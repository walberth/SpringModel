package com.project.springmodel.repository.implementation;

import com.project.springmodel.model.Person;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("PersonRepository")
public class PersonRepository   {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersonByName(String name) {
        @SuppressWarnings("unchecked")
        List<Person> person = entityManager
                .createQuery("select l from person l where l.first_name like :state")
                .setParameter("state", name + "%").getResultList();
        return person;
    }
}
