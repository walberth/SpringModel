package com.project.xxxxx.service.implementation;

import com.project.xxxxx.configuration.jwt.JwtUtil;
import com.project.xxxxx.model.*;
import com.project.xxxxx.repository.IPersonRepository;
import com.project.xxxxx.repository.ISessionRepository;
import com.project.xxxxx.repository.IUserRepository;
import com.project.xxxxx.service.ISecurityService;
import com.project.xxxxx.transversal.Response;
import com.project.xxxxx.transversal.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("SecurityService")
public class SecurityService implements ISecurityService {
    private IPersonRepository personRepository;
    private IUserRepository userRepository;
    private ISessionRepository sessionRepository;
    private JavaMailSender emailSender;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public SecurityService(IPersonRepository personRepository,
                           AuthenticationManager authenticationManager,
                           JavaMailSender emailSender,
                           JwtUtil jwtUtil,
                           ISessionRepository sessionRepository,
                           IUserRepository userRepository){
        this.personRepository = personRepository;
        this.emailSender = emailSender;
        this.sessionRepository = sessionRepository;
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

    @Override
    @Transactional
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

        final String token = this.jwtUtil.generateToken(userInformation);

        this.sessionRepository.deleteSession(userInformation.getUsername());

        Integer sessionCreated = this.sessionRepository.createSession(this.getSessionInformation(token, userInformation));

        if(sessionCreated == null || sessionCreated.equals(0)) {
            response.setMessage("Can't be created the session");

            return response;
        }

        response.setData(new JwtResponse(token));
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<JwtResponse> refresh(String token) {
        Response<JwtResponse> response = new Response<>();
        String refreshedToken = "";

        if (this.jwtUtil.canTokenBeRefreshed(token)) {
            refreshedToken = this.jwtUtil.refreshToken(token);

            if(refreshedToken.equals("")) {
                response.setMessage("Error when refresh token");

                return response;
            }

            this.updateSession(refreshedToken);
        } else {
            response.setMessage("Can't refresh token");

            return response;
        }

        response.setData(new JwtResponse(refreshedToken));
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<User> create(Person person, String username) {
        return null;
    }

    private Session getSessionInformation(String token, User userInformation){
        Date created = this.jwtUtil.getIssuedAtDateFromToken(token);
        Date expired = this.jwtUtil.getExpirationDateFromToken(token);

        Session session = new Session();
        session.setIdUser(userInformation.getId());
        session.setToken(token);
        session.setTimeToRelease((expired.getTime() - created.getTime())/1000);
        session.setExpirationTime(TimeHelper.convertToLocalDateTimeViaInstant(expired));

        return session;
    }

    private void updateSession(String refreshedToken) {
        Date expired = this.jwtUtil.getExpirationDateFromToken(refreshedToken);
        this.sessionRepository.updateSession(this.jwtUtil.getUsernameFromToken(refreshedToken),
                                             refreshedToken,
                                             TimeHelper.convertToLocalDateTimeViaInstant(expired));
    }
}
