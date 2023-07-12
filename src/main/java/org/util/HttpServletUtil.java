package org.util;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

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

    public static void httpClientCall(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String uri, String method, Map<String,String> headers){
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.start();
            ContentResponse contentRes = null;
            Request request = httpClient.newRequest(uri);
            if(httpServletRequest.getMethod().equals("GET")) {
                if(headers != null) 	{
                    setHeader(request,headers);
                }
                contentRes = request.method(HttpMethod.GET).send();
            }else if(httpServletRequest.getMethod().equals("POST")){
                if(headers != null) 	setHeader(request,headers);
                contentRes = request.method(HttpMethod.POST).send();
            }

            httpServletResponse.setStatus(contentRes.getStatus());
            httpServletResponse.getWriter().write(contentRes.getContentAsString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                httpClient.stop();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void setHeader(Request request, Map<String, String> headers) {
        Iterator iter = headers.keySet().iterator();
        while(iter.hasNext()) {
            String key = (String) iter.next();
            request.header(key, headers.get(key));
        }
    }
}
