package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    // Insertar personas
    Person insertPerson(UUID id, Person person) throws Exception;

    default Person insertPerson(Person person) throws Exception {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    // Devolvemos los registros de personas
    List<Person> selectAllPeople();

    int deletePersonById(UUID id) throws Exception;

    int updatePersonById(UUID id, Person person) throws Exception;


    Optional<Person> selectPersonById(UUID id);
}
