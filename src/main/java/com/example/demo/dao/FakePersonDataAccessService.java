package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Repositorio
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    // Lista para añadir personas
    private static List<Person> DB = new ArrayList<>();

    // Metodo para agregar personas
    @Override
    public Person insertPerson(UUID id, Person person) throws Exception {
        DB.add(new Person(id, person.getName()));
        return new Person(id, person.getName());
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {

        Optional<Person> maybePerson = selectPersonById(id);

        if(maybePerson.isEmpty()){
            return 0;
        }
        DB.remove(maybePerson.get());
        return 1;

    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    int indexOfPerson = DB.indexOf(p);

                    if(indexOfPerson >= 0) {
                        /*
                        * El objeto Person devuelve una Exception en caso de error con el método
                        * getName() y es por eso que tenemos que hacer un try/catch para poder ejecutarlo
                        * */
                        try {

                            // Si se ha encontrado la id del usuario se asigna el nuevo nombre que le vamos a pasar como parámetro
                            DB.set(indexOfPerson, new Person(id, person.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Devolvemos 1 si se ha ejecutado correctamente
                        return 1;
                    }

                    // En caso contrario devolvemos un 0
                    return 0;

                }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> {
                    try {
                        return person.getId().equals(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .findFirst();
    }
}
