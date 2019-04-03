package com.example.balancerapplication.java_processing;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NodeStorage {

    private Map<String,JsonNode> storage;

    public NodeStorage(){
        this.storage=new TreeMap<>();// or HashMap?
    }

    public NodeStorage(Map<String, JsonNode> storage) {
        this.storage = storage;
    }

    //TO DO: constructor with parameters String,JsonNode taking it from JsonReader


    public Map<String, JsonNode> getStorage() {
        return storage;
    }

    public  JsonNode getNodeByName(String name){
        return this.storage.get(name);
    }

    public int getSize(){
        return this.storage.size();
    }

    public boolean containsName(String name){
        return this.storage.containsKey(name);
    }

    public boolean containsNode(JsonNode node){
        return this.storage.containsValue(node);
    }

    public void setStorage(Map<String, JsonNode> storage) {
        this.storage = storage;
    }

    public void addNewNode(String name,JsonNode node){
        this.storage.put(name,node);
    }

    public void addNewNodes(Map<String,JsonNode> newNodes){
        this.storage.putAll(newNodes);
    }

    public void deleteNodeByName(String name){
        this.storage.remove(name);
    }

    public JsonNode getJsonNodeByValue (String nameofNode,String nameOfValue){
        return  this.storage.get(nameofNode).findValue(nameOfValue);
    }

    public List<JsonNode> getNodesByFieldName(String nameOfNode, String fieldName){
        return this.storage.get(nameOfNode).findValues(fieldName);
    }

    public List<String> getNodesAsTextByFieldName(String nameOfNode, String fieldName){
        return this.storage.get(nameOfNode).findValuesAsText(fieldName);
    }

    public List<String> getNodeFieldNames(String nameOfNode){
        Iterator<String> it= this.storage.get(nameOfNode).fieldNames();
        List<String> fieldNames=new ArrayList<>();
        while(it.hasNext()){
            fieldNames.add(it.next());
        }
        return fieldNames;
    }

    public List<String> getNodeElementsToString(String nameOfNode){
        Iterator<JsonNode> it= this.storage.get(nameOfNode).elements();
        List<String> elementsToString=new ArrayList<>();
        while(it.hasNext()){
            elementsToString.add(String.valueOf(it.next()));
        }
        return elementsToString;
    }
    //TO DO: Add some other methods using
    // https://fasterxml.github.io/jackson-databind/javadoc/2.2.0/com/fasterxml/jackson/databind/JsonNode.html

    @Override
    public String toString() {
        return "NodeStorage{" +
                "storage=" + storage +
                '}';
    }

}
