package com.project.xxxxx.service;

import com.project.xxxxx.model.JwtRequest;
import com.project.xxxxx.model.JwtResponse;
import com.project.xxxxx.model.Person;
import com.project.xxxxx.transversal.Response;

public interface ISecurityService {
    void CreatePerson(Person person, Person person2);
    void UpdatePerson(Person person);
    void DeletePerson();
    void sendSimpleMessage(String to, String subject, String text);
    Response<JwtResponse> authenticate(JwtRequest request);
    Response<JwtResponse> refresh(String token);
}
