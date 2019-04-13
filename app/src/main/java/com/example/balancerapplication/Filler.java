package com.example.balancerapplication;

import com.example.balancerapplication.java_processing.NodeStorage;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;



public class Filler implements Serializable{

    private ObjectMapper mapper = new ObjectMapper();


    @Getter
    public class Entity implements Serializable {
        private String name;
        private Map<String, Integer> body;

        Entity(){
            this.name = null;
            this.body = new TreeMap<>();
        }

        Entity(Entity u){
            this.name = u.name;
            for(Map.Entry<String, Integer> entry : u.body.entrySet()) {
                this.body.put(entry.getKey(), entry.getValue());

            }
        }

        Entity(String name, JsonNode node){
            this.name = name;
            Map<String, Integer> res = mapper.convertValue(node, Map.class);
            this.body.putAll(res);
        }

        public String getName(){
            return this.name;
        }
    }

    @Getter
    public class Environment implements Serializable {
        private String name;
        private Map<String, Integer> body;

        Environment(){
            this.name = null;
            this.body = new TreeMap<>();
        }

        Environment(Environment u){
            this.name = u.name;
            for(Map.Entry<String, Integer> entry : u.body.entrySet()) {
                this.body.put(entry.getKey(), entry.getValue());
            }
        }

        Environment(String name, JsonNode node){
            this.name = name;
            Map<String, Integer> res = mapper.convertValue(node, Map.class);
            this.body.putAll(res);
        }

        public String getName(){
            return this.name;
        }
    }

    @Getter
    public class Modifier implements Serializable {
        private String name;
        private Map<String, Integer> body;

        Modifier(){
            this.name = null;
            this.body = new TreeMap<>();
        }

        Modifier(Modifier u){
            this.name = u.name;
            for(Map.Entry<String, Integer> entry : u.body.entrySet()) {
                this.body.put(entry.getKey(), entry.getValue());

            }
        }

        Modifier(String name, JsonNode node){
            this.name = name;
            Map<String, Integer> res = mapper.convertValue(node, Map.class);
            this.body.putAll(res);
        }

        public String getName(){
            return this.name;
        }
    }

    private ArrayList<Entity> Entitys;
    private ArrayList<Environment> Environments;
    private ArrayList<Modifier> Modifiers;

    Filler() {
        this.Entitys = new ArrayList<>();
        this.Environments = new ArrayList<>();
        this.Modifiers = new ArrayList<>();
    }

    Filler(NodeStorage nodestorage) {
        this.Entitys = new ArrayList<>();
        for (Map.Entry<String, JsonNode> entity : nodestorage.getEntitiesNodes().entrySet()) {
            Entity e = new Entity(entity.getKey(), entity.getValue());
            this.Entitys.add(e);
        }

        this.Modifiers = new ArrayList<>();
        for (Map.Entry<String, JsonNode> modifier : nodestorage.getModificatorsNodes().entrySet()) {
            Modifier m = new Modifier(modifier.getKey(), modifier.getValue());
            this.Modifiers.add(m);
        }

        this.Environments = new ArrayList<>();
        for (Map.Entry<String, JsonNode> environment : nodestorage.getEnvironmentsNodes().entrySet()) {
            Environment en = new Environment(environment.getKey(), environment.getValue());
            this.Environments.add(en);
        }


    }

    public ArrayList<Entity> getEntitiesObj() {
        return Entitys;
    }
    public ArrayList<Environment> getEnvironmentObj() {
        return Environments;
    }
    public ArrayList<Modifier> getModifiersObj() {
        return Modifiers;
    }


}

