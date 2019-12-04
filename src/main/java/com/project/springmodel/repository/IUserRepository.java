package com.project.springmodel.repository;

import com.project.springmodel.model.User;

public interface IUserRepository {
    User getUserInformation(String username, String password);
    User getUser(String username);
    User createUser(User user);
    Integer validateUserExists(String username);
}
