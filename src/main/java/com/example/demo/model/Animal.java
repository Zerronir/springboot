package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Animal {

    private final UUID id;
    @NonNull
    private final String name;

    @NonNull
    private final String owner;

    // Creamos el constructor de la clase Animal que implementará tamibén la clase Persona para vincularlo con el dueño
    public Animal(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("owner") String owner) throws Exception {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    // Creamos los setters y getters
    public UUID getId() throws Exception{
        return id;
    }

    public String getPetName() throws Exception{
        return name;
    }

    public String getOwnerDni()throws Exception{
        return owner;
    }
}
