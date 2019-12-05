package com.project.springmodel.repository.mapper;

import com.project.springmodel.model.Person;
import com.project.springmodel.transversal.TimeHelper;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setFirstName(rs.getString("firstName"));
        person.setFatherName(rs.getString("fatherLastName"));
        person.setMotherName(rs.getString("motherLastName"));
        person.setSex(rs.getInt("sex"));
        person.setBirthDate(rs.getDate("birthDate").toLocalDate());
        person.setDocument(rs.getString("document"));
        person.setEmail(rs.getString("email"));
        person.setTelephone(rs.getString("telephone"));
        person.setMobile(rs.getString("mobile"));
        person.setUserRegister(rs.getString("userRegister"));
        person.setTimeStamp(TimeHelper.convertToLocalDateTimeViaInstant(rs.getDate("timeStamp")));

        return person;
    }
}
