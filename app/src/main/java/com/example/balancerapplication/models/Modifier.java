package com.example.balancerapplication.models;

import java.io.Serializable;

public class Modifier implements Serializable {

    private String name;

    public Modifier(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
