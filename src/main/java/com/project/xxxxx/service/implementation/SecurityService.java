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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SecurityService")
public class SecurityService implements ISecurityService {
    private IPersonRepository personRepository;
    private IUserRepository userRepository;
    private JavaMailSender emailSender;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public SecurityService(IPersonRepository personRepository,
                           AuthenticationManager authenticationManager,
                           JavaMailSender emailSender,
                           JwtUtil jwtUtil,
                           IUserRepository userRepository){
        this.personRepository = personRepository;
        this.emailSender = emailSender;
        this.userRepository = userRepository;
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

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }

    public Response<JwtResponse> authenticate(JwtRequest request) {
        Response<JwtResponse> response = new Response<>();

        if(request.getUsername().equals("") || request.getPassword().equals("")) {
            response.setMessage("Must be indicate user and password");

            return response;
        }

        User userInformation = this.userRepository.getUserInformation(request.getUsername(), request.getPassword());

        if(userInformation == null) {
            response.setMessage("User Not Found");

            return response;
        }

        if(!userInformation.isEnabled()) {
            response.setMessage("User Disabled");

            return response;
        }

        final String token = jwtUtil.generateToken(userInformation);

        response.setData(new JwtResponse(token));
        response.setIsWarning(false);

        return response;
    }

    public Response<JwtResponse> refresh(String token) {
        Response<JwtResponse> response = new Response<>();
        String refreshedToken = "";

        if (this.jwtUtil.canTokenBeRefreshed(token)) {
            refreshedToken = this.jwtUtil.refreshToken(token);

            if(refreshedToken.equals("")) {
                response.setMessage("Error when refresh token");

                return response;
            }
        } else {
            response.setMessage("Can't refresh token");

            return response;
        }

        response.setData(new JwtResponse(refreshedToken));
        response.setIsWarning(false);

        return response;
    }
}
