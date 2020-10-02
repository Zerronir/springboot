package com.example.demo.service;

import com.example.demo.dao.AnimalDao;
import com.example.demo.model.Animal;
import com.example.demo.model.AnimalData;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalDao animalDao;

    @Autowired
    public AnimalService(@Qualifier("postgresAnimal")AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    // Insertamos al animal en la base de datos
    public void insertAnimal(Animal animal, String person) throws Exception {
        animalDao.insertAnimal(animal, person);
    }

    public List<AnimalData> getAllAnimals() throws Exception {
        return animalDao.getAllAnimals();
    }
}
