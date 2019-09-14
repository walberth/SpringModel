package com.project.xxxxx.service.implementation;

import com.project.xxxxx.model.Person;
import com.project.xxxxx.repository.IPersonRepository;
import com.project.xxxxx.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;

@Service("SecurityService")
public class SecurityService implements ISecurityService {
    private IPersonRepository personRepository;
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public SecurityService(IPersonRepository personRepository){
        this.personRepository = personRepository;
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
        emailSender.send(message);
    }
}
