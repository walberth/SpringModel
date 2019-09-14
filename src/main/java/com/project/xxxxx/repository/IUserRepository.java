package com.project.xxxxx.repository;

import com.project.xxxxx.model.User;

public interface IUserRepository {
    User getUserInformation(String username, String password);
}
