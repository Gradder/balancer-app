package com.example.balancerapplication.java_processing;

import com.example.balancerapplication.Filler.Entity;
import com.fasterxml.jackson.databind.JsonNode;


import java.util.Map;
import java.util.TreeMap;

public class NodeStorage {

    public static final NodeStorage INSTANCE=new NodeStorage();


    private final Map<String,JsonNode> entitiesStorage;
    private final Map<String,JsonNode> modificatorsStorage;
    private final Map<String,JsonNode> environmentsStorage;

    private NodeStorage(){
        this.entitiesStorage=new TreeMap<>();
        this.modificatorsStorage=new TreeMap<>();
        this.environmentsStorage=new TreeMap<>();
    }

//ENTITIES
    public Map<String, JsonNode> getEntitiesNodes() { return entitiesStorage; }

    public  JsonNode getEntityNodeByName(String name){
        return this.entitiesStorage.get(name);
    }

    public void addEntityNode(String name,JsonNode node){
        this.entitiesStorage.put(name,node);
    }

    public void addEntitiesNodes (Map<String,JsonNode> newNodes){
        this.entitiesStorage.putAll(newNodes);
    }

    //MODIFICATORS
    public Map<String, JsonNode> getModificatorsNodes() {
        return modificatorsStorage;
    }

    public  JsonNode getModificatorNodeByName(String name){
        return this.modificatorsStorage.get(name);
    }

    public void addModificatorNode(String name,JsonNode node){
        this.modificatorsStorage.put(name,node);
    }

    public void addModificatorsNodes (Map<String,JsonNode> newNodes){
        this.modificatorsStorage.putAll(newNodes);
    }

    //ENVIRONMENTS
    public Map<String, JsonNode> getEnvironmentsNodes() {
        return environmentsStorage;
    }

    public  JsonNode getEnvironmentNodeByName(String name){
        return this.entitiesStorage.get(name);
    }

    public void addEnvironmentNode(String name,JsonNode node){
        this.environmentsStorage.put(name,node);
    }

    public void addEnvironmentsNodes (Map<String,JsonNode> newNodes){
        this.environmentsStorage.putAll(newNodes);
    }

}
