package com.example.demo.api;

import com.example.demo.model.Animal;
import com.example.demo.model.AnimalData;
import com.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/animal")
public class AnimalController {
    private final AnimalService animalService;

    // Controlador de la clase Animal
    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Creamos el post controller para el evento de crear el animal
    @PostMapping
    public void insertAnimal(@Validated @NonNull @RequestBody Animal animal, String owner) throws Exception{
        animalService.insertAnimal(animal, owner);
    }

    // Método para listar todos los animales de la base de datos
    @GetMapping
    public List<AnimalData> getAllAnimals() throws Exception {
        return animalService.getAllAnimals();
    }

}
