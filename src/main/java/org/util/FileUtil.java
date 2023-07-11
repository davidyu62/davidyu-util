package org.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    /**
     * 특정 path에 있는 파일을 읽어서 String 으로 반환
     * */
    public static String readToFile(String path) throws IOException {
        return Files.lines(new File(path).toPath()).collect(Collectors.joining(System.lineSeparator()));
    }

//    public static byte[] readToFile(String path){
//            return Files.readAllBytes(Paths.get(path));
//    }

    /**
     * String 을 특정 path 파일에 create (append 시에는 StandardOpenOption 변경)
     * */
    public static void writeToFile(String str, String path){
        File file = new File(path);

        try {
            if(!file.exists())  file.createNewFile();
            Files.write(Paths.get(path), str.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
