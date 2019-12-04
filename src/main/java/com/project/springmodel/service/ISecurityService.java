package com.project.springmodel.service;

import com.project.springmodel.model.JwtRequest;
import com.project.springmodel.model.JwtResponse;
import com.project.springmodel.model.Person;
import com.project.springmodel.model.User;
import com.project.springmodel.transversal.Response;

public interface ISecurityService {
    void CreatePerson(Person person, Person person2);
    void UpdatePerson(Person person);
    void DeletePerson();
    Response<JwtResponse> authenticate(JwtRequest request);
    Response<JwtResponse> refresh(String token);
    Response<User> create(Person person, String username, String password);
}
