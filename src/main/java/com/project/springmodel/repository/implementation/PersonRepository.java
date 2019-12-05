package com.project.springmodel.repository.implementation;

import com.project.springmodel.model.Person;
import com.project.springmodel.repository.IPersonRepository;
import com.project.springmodel.repository.mapper.PersonRowMapper;
import com.project.springmodel.transversal.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("PersonRepository")
public class PersonRepository   {
    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    public PersonRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public List<Person> getPersonByName(String name) {
        @SuppressWarnings("unchecked")
        List<Person> locs = entityManager
                .createQuery("select l from person l where l.first_name like :state")
                .setParameter("state", name + "%").getResultList();
        return locs;
    }
}
