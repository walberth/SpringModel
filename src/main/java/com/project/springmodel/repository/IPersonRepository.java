package com.project.springmodel.repository;

import com.project.springmodel.model.Person;
import java.util.List;

public interface IPersonRepository {
    Person getPersonById(int idPerson);
    List<Person> getPersonList(int rowsPerPage, int pageNumber);
    List<Person> getPersonListByRowMapper(int rowsPerPage, int pageNumber);
    List<Person> getPersonListByRowMapperAndExtrapolation(int rowsPerPage, int pageNumber);
    void updatePerson(Person person);
    void deletePerson(int idPerson);
    Integer createPerson(Person person);
    Person createAndGetPersonCreated(Person person);
}
