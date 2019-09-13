package com.project.xxxxx.service.implementation;

import com.project.xxxxx.model.Person;
import com.project.xxxxx.repository.IPersonRepository;
import com.project.xxxxx.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;

@Service("SecurityService")
public class SecurityService implements ISecurityService {
    private IPersonRepository personRepository;

    @Autowired
    public SecurityService(IPersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Transactional
    public void CreatePerson(Person person, Person person2) {
        System.setProperty("methodName", "CreatePerson");

        Person personCreated2 = this.personRepository.createAndGetPersonCreated(person);

        int personCreated1 = this.personRepository.createPerson(person2);
    }
}
