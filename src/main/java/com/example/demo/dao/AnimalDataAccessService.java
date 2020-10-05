package com.example.demo.dao;

import com.example.demo.model.Animal;
import com.example.demo.model.AnimalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("postgresAnimal")
public class AnimalDataAccessService implements AnimalDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnimalDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
    *
    * El método de insert animal acepta un objeto de tipo animal
    * del que obtendremos los valores de nombre y dni del propietario
    * entonces lo que haremos será crear un uuid para la mascota.
    *
    * @params id --> Le pasamos una id para el animal
    * @params animal --> pasamos un objeto de tipo Animal con sus características
    * @params owner --> Pasamos una string con el nombre del owner de la mascota
    *
    * */
    @Override
    public Animal insertAnimal(UUID id, Animal animal, String owner) throws Exception {
        // Creamos la variable que mandaremos al servidor
        final String insertSQL = "INSERT INTO animal(id, name, owner) VALUES (?, ?, ?)";
        // Creamos los parametros que enviaremos junto a la query
        UUID animalId = UUID.randomUUID();
        String name = animal.getPetName();
        String own = animal.getOwnerDni();

        try {

            //Intentamos hacer un insert
            jdbcTemplate.update(insertSQL, animalId, name, own);

            // Si es correcto devolverá un nuevo objeto de tipo Animal
            return new Animal(animalId, name, own);

        }catch (Exception e){
            // En caso de que haya un error haremos que el servidor nos devuelva un error
            throw new Exception(e);
        }
    }

    @Override
    public List<AnimalData> getAllAnimals() throws Exception {

        final String getAnimals = "SELECT an.id, an.name, ow.name FROM animal an LEFT JOIN person ow ON an.owner = ow.dni";

        return jdbcTemplate.query(getAnimals, (resultSet, i) -> {
            /**
             *
             * Creamos los datos que vamos a devolver al usuario en un json
             *
             * @params petId => Id de la mascota
             * @params petName => Animal de la mascota
             * @params ownerName => Nombre del dueño de la mascota
             *
             * */
            UUID petId = UUID.fromString(resultSet.getString(1));
            String petName = resultSet.getString(2);
            String ownerName = resultSet.getString(3);

            return new AnimalData(petId, petName, ownerName);

        });

    }

}
