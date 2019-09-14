package com.project.xxxxx.service.implementation;

import com.project.xxxxx.configuration.jwt.JwtUtil;
import com.project.xxxxx.model.JwtRequest;
import com.project.xxxxx.model.JwtResponse;
import com.project.xxxxx.model.Person;
import com.project.xxxxx.model.User;
import com.project.xxxxx.repository.IPersonRepository;
import com.project.xxxxx.repository.IUserRepository;
import com.project.xxxxx.service.ISecurityService;
import com.project.xxxxx.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service("SecurityService")
public class SecurityService implements ISecurityService, UserDetailsService {
    private IPersonRepository personRepository;
    private IUserRepository userRepository;
    private JavaMailSender emailSender;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityService(IPersonRepository personRepository,
                           AuthenticationManager authenticationManager,
                           JavaMailSender emailSender,
                           @Qualifier("SecurityService") UserDetailsService userDetailsService,
                           JwtUtil jwtUtil,
                           IUserRepository userRepository){
        this.personRepository = personRepository;
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public void CreatePerson(Person person, Person person2) {
        Person personCreated2 = this.personRepository.createAndGetPersonCreated(person);

        int personCreated1 = this.personRepository.createPerson(person2);
    }

    public void UpdatePerson(Person person) {
    }

    public void DeletePerson() {
        String methodName = System.getProperty("methodName");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInformation = this.userRepository.getUserInformation("wgutierrez", "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e");

        if (userInformation == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return userInformation;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }

    public Response<JwtResponse> createAuthenticationToken(JwtRequest request) {
        Response<JwtResponse> response = new Response<>();

        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        final String token = jwtUtil.generateToken(userDetails);

        response.setData(new JwtResponse(token));
        response.setIsWarning(false);

        return response;
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
