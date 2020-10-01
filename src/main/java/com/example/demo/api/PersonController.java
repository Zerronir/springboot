package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// Habilitamos que sea un controlador de tipo REST --> @RestController
// Creamos el request para poder acceder al endpoint de persona --> @RequestMapping("api/v1/person")
// Para que sea un post request -- > @PostMapping
// Para que sea un get request -- > @GetMapping
// Para que sea un put request -- > @PutMapping
// Para que sea un delete request -- > @DeleteMapping
// Podemos indicar un path de la siguiente manera para referirnos a un paramtero -- > @PathVariable("")
// También podemos indicar que path vamos a seleccionar -- > (path = "{path_name}")


@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void insertPerson(@Validated @NonNull @RequestBody Person person) throws Exception {
        personService.insertPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    // Seleccionar por id
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    // Eliminamos un usuario por su id
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person newPerson){
        personService.updatePerson(id, newPerson);
    }


}
