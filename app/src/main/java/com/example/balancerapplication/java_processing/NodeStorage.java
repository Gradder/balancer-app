package com.example.balancerapplication.java_processing;

import android.content.Context;

import com.example.balancerapplication.Reader;
import com.fasterxml.jackson.databind.JsonNode;


import org.w3c.dom.Node;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class NodeStorage {


    private final Reader reader;
    private final Map<String,JsonNode> entitiesStorage;
    private final Map<String,JsonNode> modificatorsStorage;
    private final Map<String,JsonNode> environmentsStorage;

    public NodeStorage(Context context){
        this.reader = new Reader(context);
        this.entitiesStorage=new TreeMap<>();
        this.modificatorsStorage=new TreeMap<>();
        this.environmentsStorage=new TreeMap<>();

        try {
            JsonNode unitsLibrary = reader.getNode(context, "JSON_units_library.json");
            this.entitiesStorage.putAll(reader.getEntityNodesWithNoContainersInside(unitsLibrary));

            JsonNode modifiersLibrary = reader.getNode(context, "JSON_modifiers_library.json");
            this.modificatorsStorage.putAll(reader.getAllNodesFromRoot(modifiersLibrary));

            JsonNode environmentsLibrary = reader.getNode(context, "JSON_environment_library.json");
            this.environmentsStorage.putAll(reader.getAllNodesFromRoot(environmentsLibrary));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//ENTITIES
    public Map<String, JsonNode> getEntitiesNodes() {
        return entitiesStorage;
    }

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
