package com.example.demo.model;

import java.util.UUID;

public class AnimalData {
    private final UUID id;
    private final String petName;
    private final String ownerName;

    public AnimalData(UUID id, String petName, String ownerName){
        this.id = id;
        this.petName = petName;
        this.ownerName = ownerName;
    }

    public UUID getPetId(){
        return id;
    }

    public String getPet() {
        return petName;
    }

    public String getOwnerName(){
        return ownerName;
    }

}