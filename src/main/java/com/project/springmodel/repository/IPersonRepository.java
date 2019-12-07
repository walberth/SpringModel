package com.project.springmodel.repository;

import com.project.springmodel.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonRepository extends CrudRepository<Person, Long> {
    Person findById(long id);
    @Query("SELECT c FROM Person c WHERE c.FirstName = :firstName")
    List<Person> findByName(@Param("firstName") String firstName);
}
