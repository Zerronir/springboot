package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Veterinary {

    private UUID id;
    @NonNull
    private String vetName;
    @NonNull
    private String vetAddress;
    @NonNull
    private char vetCIF;
    @NonNull
    private int vetPhone;
    @NonNull
    private String vetMail;
    @NonNull
    private char vetCountry;

    public Veterinary(@JsonProperty("id") UUID id,
                      @JsonProperty("vetName") String name){
        this.id = id;
        this.vetName = name;
    }


}
