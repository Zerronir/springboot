package com.example.demo.dao;

import com.example.demo.model.Animal;

import java.util.UUID;

public interface AnimalDao {

    // Insertar animal
    Animal insertAnimal(UUID id, Animal animal) throws Exception;

}
