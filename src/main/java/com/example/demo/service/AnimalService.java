package com.example.demo.service;

import com.example.demo.dao.AnimalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalDao animalDao;

    @Autowired
    public AnimalService(@Qualifier("postgresAnimal")AnimalDao animalDao) {
        this.animalDao = animalDao;
    }
}
