package org.util;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GsonUtil {

    /**
     *  Object 를 json 파일로 write
     * */
    public static void writeToFileWithObject(Object object, String path){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = new FileWriter(path);
            gson.toJson(object,writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json 파일을 Object로 읽어 오기
     * */
    public static Object readFromFileWithObject(String path){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader(path);
            Object object = gson.fromJson(reader, Object.class);
            return object;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFromFileWithString(String path){
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readValue(String path, String key){
        Gson gson = new Gson();
        String jsonStr = readFromFileWithString(path);
        JsonObject jsonObject = gson.fromJson(jsonStr,JsonObject.class);
        return jsonObject.get(key).getAsString();
    }

}