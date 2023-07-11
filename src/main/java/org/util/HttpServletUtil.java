package org.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpServletUtil {

    /**
     *  get body Method.POST
     * */
    public static String readBody(HttpServletRequest req){
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream;
        String body = null;
        try {
            // extract body
            inputStream = req.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
            body = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return body;
    }
}
