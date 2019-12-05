package com.project.springmodel.repository;

import com.project.springmodel.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<Person, Long> {
    Person findById(long id);
}
