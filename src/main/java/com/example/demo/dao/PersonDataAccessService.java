package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) throws Exception {

        final String insertSQL = "INSERT INTO person (name) VALUES (?)";
        
        String name = person.getName();

        try{
            
            jdbcTemplate.execute(insertSQL);
            
        }catch (Exception e){
            throw new Exception(e);
        }

        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            /*
                Creamos una variable de tipo UUID para pasarle el id del resultset del usuario
                Y crearemos también otra variable de tipo String para pasarle el nombre que recibamos
                en el resultset del usuario
             */
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");

            /*
             * Una vez tenemos todos los datos del result set creamos un nuevo objeto de tipo Person pasándole
             * las variables que acabamos de crear nosotros antes
             * */
            return new Person(id, name);

        }));
    }

    @Override
    public int deletePersonById(UUID id) {

        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String selectById = "SELECT * FROM person WHERE id = ?";

        // Creamos una query para devolver un objeto, para ello usamos la función queryForObject donde tenemos que pasarle un objeto con el parámetro que usamos para la query
        Person person = jdbcTemplate.queryForObject(selectById, new Object[]{id},
                (resultSet, i) -> {
                    UUID userId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Person(userId, name);
                }
        );

        // Devolvemos un Optional que puede ser nulo donde le pasamos el objeto de tipo Person
        return Optional.ofNullable(person);

    }
}
