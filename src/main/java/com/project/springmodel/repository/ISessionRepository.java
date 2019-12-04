package com.project.springmodel.repository;

import com.project.springmodel.model.Session;
import java.time.LocalDateTime;

public interface ISessionRepository {
    Integer createSession(Session session);
    void updateSession(String username, String token, LocalDateTime expirationTime);
    void deleteSession(String username);
}
