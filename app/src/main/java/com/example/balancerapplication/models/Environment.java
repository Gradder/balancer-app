package com.example.balancerapplication.models;

import java.io.Serializable;

public class Environment implements Serializable {

    private String name;

    public Environment(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
