package com.example.balancerapplication;

import android.content.Context;
import android.os.Environment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;


public class Reader {

    /*выгрузка библиотек в Internal storage. В манифесте запросили разрешение WRITE_EXTERNAL_STORAGE */

    public Reader(Context context){
        try {
            readFileOnInternalStorage(context, "JSON_units_library.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileOnInternalStorage(Context context, String fileName, String textToWrite){
        //получаем каталог, который используется только данным приложением, вызвав getExternalFilesDir()
        //создаем новый файл директории
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), fileName);
        if(!file.exists()){
            file.mkdir();
        }

        try{
            FileWriter writer = new FileWriter(file);
            writer.append(textToWrite);
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String readFileOnInternalStorage(Context context, String fileName) throws IOException {
        // получаем путь к внешнему диску
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS).toString());
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, fileName);
        // открываем поток для чтения
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sdFile));
        byte[] tempArray = new byte[(int) sdFile.length()];
        bufferedInputStream.read(tempArray);
        return tempArray.toString();

    }


    public JsonNode getNode(Context context, String fileName) throws IOException{
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS).toString());
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, fileName);
        // Получаем доступ к ноде
        JsonNode jsonNode = new ObjectMapper().readTree(sdFile);
        return jsonNode;
    }

    //get name of entity
    public String getStringKey(JsonNode jsonNode){
        return jsonNode.fieldNames().next();
    }

    // Берет все узлы, которые не содержат в себе других объектов или массивов
    public Map<String, JsonNode> getEntityNodesWithNoContainersInside(JsonNode root){

        Map<String, JsonNode> result = new TreeMap<>();
        Queue<Map.Entry<String, JsonNode>> queue = new LinkedList<>();

        // создание изначальной очереди
        Iterator<Map.Entry<String, JsonNode>> iterator = root.fields();
        while (iterator.hasNext()){
            queue.add(iterator.next());
        }

        /* Проходя по очереди, проверяем содержимое узлов, если нет объектов-контейнеров, то
        заносим узел в результат, иначе добавляем все найденные объекты-контейнеры в очередь*/
        Map.Entry<String, JsonNode> node;
        Map.Entry<String, JsonNode> nextNode;
        boolean isEntity;
        while (!queue.isEmpty()){
            node = queue.poll();
            isEntity = true;
            iterator = node.getValue().fields();
            while (iterator.hasNext()){
                nextNode = iterator.next();
                if (nextNode.getValue().isContainerNode()){
                    isEntity = false;
                    queue.add(nextNode);
                }
            }
            if (isEntity){
                result.put(node.getKey(), node.getValue());
            }
        }
        return result;
    }

    // Берет все узлы, которые лежат непосредственно в корне
    public Map<String, JsonNode> getAllNodesFromRoot(JsonNode root){
        Map<String, JsonNode> result = new TreeMap<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = root.fields();
        Map.Entry<String, JsonNode> nextNode;
        while (iterator.hasNext()){
            nextNode = iterator.next();
            result.put(nextNode.getKey(), nextNode.getValue());
        }
        return result;
    }
}