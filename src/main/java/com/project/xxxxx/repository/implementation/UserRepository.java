package com.project.xxxxx.repository.implementation;

import com.project.xxxxx.model.User;
import com.project.xxxxx.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public class UserRepository implements IUserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserInformation(String username, String password) {
        try {
            return jdbcTemplate.queryForObject(String.format("CALL getUserInformation('%s', '%s')",
                    username,
                    password),
                    (rs, rowNum) ->
                            new User(rs.getInt("id"),
                                    rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getInt("idPerson"),
                                    rs.getString("userRegister"),
                                    rs.getDate("timeStamp").toLocalDate(),
                                    rs.getBoolean("active"),
                                    rs.getString("role")));
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public User getUser(String username) {
        try {
            return jdbcTemplate.queryForObject(String.format("CALL getUser('%s')",
                    username),
                    (rs, rowNum) ->
                            new User(rs.getString("username"),
                                    rs.getString("role")));
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
