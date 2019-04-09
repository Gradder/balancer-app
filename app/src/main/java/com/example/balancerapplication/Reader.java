package com.example.balancerapplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Reader {
    public static JsonNode getUnitsNode() throws Exception{

        //read json file data to String
        byte[] jsonData = Files.readAllBytes(Paths.get("JSON_units_library.json"));
        JsonNode jsonNode = new ObjectMapper().readTree(jsonData);
        return jsonNode;
    }
    public static JsonNode getModifiersNode() throws IOException{
        //read json file data to String
        byte[] jsonData = Files.readAllBytes(Paths.get("JSON_modifiers_library.json"));
        JsonNode jsonNode = new ObjectMapper().readTree(jsonData);
        return jsonNode;
    }
    public static JsonNode getEnvironmentNode() throws IOException{
        //read json file data to String
        byte[] jsonData = Files.readAllBytes(Paths.get("JSON_environment_library.json"));
        JsonNode jsonNode = new ObjectMapper().readTree(jsonData);
        return jsonNode;
    }
    //get name of entity
    public String getStringKey(JsonNode jsonNode){
        String str = null;
        for (Iterator<String> it = jsonNode.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            str = key;
        }
        return str;
    }
}