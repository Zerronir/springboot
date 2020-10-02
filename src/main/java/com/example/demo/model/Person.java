package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Person {

    private final UUID id;
    @NonNull
    private final String name;
    @NonNull
    private final String dni;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("dni") String dni) {
        this.id = id;
        this.name = name;
        this.dni = dni;
    }

    public UUID getId() throws Exception{
        return id;
    }

    public String getName() throws Exception{
        return name;
    }

    public String getDni() throws Exception {
        return dni;
    }

}
