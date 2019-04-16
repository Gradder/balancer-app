package com.example.balancerapplication;

import android.content.Context;
import android.os.Environment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.IOException;


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
        return jsonNode.fieldNames().toString();
    }
}