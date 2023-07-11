package org.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class GsonUtil {

    /**
     *  Object 를 json 파일로 write
     * */
    public static void writeObjectToJson(Object object, String path){
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
    public static Object readObjectFromJson(String path){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader(path);
            Object object = gson.fromJson(reader, Object.class);
            return object;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}