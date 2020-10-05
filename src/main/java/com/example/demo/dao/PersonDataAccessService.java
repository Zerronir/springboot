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
    public Person insertPerson(UUID id, Person person) throws Exception {

        final String insertSQL = "INSERT INTO person (id, name, dni) VALUES (?, ?, ?)";
        UUID newId = UUID.randomUUID();
        String name = person.getName();
        String dni = person.getDni();

        try{
            // Ejecutamos el insert usando el método update de JdbcTemplate
            jdbcTemplate.update(insertSQL, newId, name, dni);

            // Cuando creamos el usuario devolvemos un objeto de tipo persona
            return new Person(newId, name, dni);
        }catch (Exception e){
            throw new Exception(e);
        }
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
            String dni = resultSet.getString("dni");

            /*
             * Una vez tenemos todos los datos del result set creamos un nuevo objeto de tipo Person pasándole
             * las variables que acabamos de crear nosotros antes
             * */
            return new Person(id, name, dni);

        }));
    }

    @Override
    public int deletePersonById(UUID id) throws Exception {

        final String deleteSQL = "DELETE FROM person WHERE id = ?";

        // Realizamos la consulta a través de un método de try/catch
        try{
            // Ejecutamos el método update de la clase JdbcTemplate para poder eliminar un usuario
            jdbcTemplate.update(deleteSQL, id);

            // Si se ha eliminado correctamente recibiremos un 1
            return 1;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public int updatePersonById(UUID id, Person person) throws Exception {

        // Creamos la consulta que llevaremos a la base de datos en una string donde ? será el parametro que pasaremos
        final String updateSQL = "UPDATE person SET name = ? WHERE id = ?";

        // Creamos una variable para almacenar el nombre del objeto persona que pasamos usando el método getName()
        String name = person.getName();

        try {

            // Preparamos el statement usando la clase jdbctemplate para ello usando el método de update
            jdbcTemplate.update(updateSQL, name, id);

        }catch (Exception e){
            throw new Exception("El usuario no se ha registrado por un error al introducir los datos, por favor, prueba de nuevo o contacta con un administrador");
        }


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
                    String dni = resultSet.getString("dni");
                    return new Person(userId, name, dni);
                }
        );

        // Devolvemos un Optional nulo donde le pasamos el objeto de tipo Person
        return Optional.ofNullable(person);

    }
}
