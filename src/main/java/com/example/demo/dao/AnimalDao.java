package com.example.demo.dao;

import com.example.demo.model.Animal;
import com.example.demo.model.AnimalData;
import com.example.demo.model.Person;

import java.util.List;
import java.util.UUID;

public interface AnimalDao {

    // Insertar animal
    Animal insertAnimal(UUID id, Animal animal, String person) throws Exception;

    default Animal insertAnimal(Animal animal, String person) throws Exception {
        UUID id = UUID.randomUUID();
        return insertAnimal(id, animal, person);
    }

    // Devolvemos el registro de mascotas registradas en la bbdd
    List<AnimalData> getAllAnimals() throws Exception;

}
