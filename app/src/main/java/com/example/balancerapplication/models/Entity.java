package com.example.balancerapplication.models;

import java.io.Serializable;

public class Entity implements Serializable {

    private String name;

    public Entity(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
