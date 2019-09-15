package com.project.xxxxx.repository.implementation;

import com.project.xxxxx.model.User;
import com.project.xxxxx.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return jdbcTemplate.queryForObject(String.format("CALL getUserInformation('%s', '%s')",
                username,
                password),
                (rs, rowNum) ->
                        new User(rs.getInt("id"),
                                 rs.getInt("idPerson"),
                                 rs.getDate("timeStamp"),
                                 rs.getString("username"),
                                 rs.getString("password"),
                                 rs.getString("role"),
                                 rs.getBoolean("active"),
                                 rs.getString("userRegister")));
    }
}
