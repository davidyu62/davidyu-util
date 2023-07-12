package org.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessUtil {

    /**
     *  프로세스 call and return 없음
     * */
    public static void processExecute(String path, List<String> params) throws IOException {
        params.add(0,path);
        Process process = new ProcessBuilder(params).start();
    }

    /**
     * 프로세스 call and String 리턴
     * */
    public static String processExecuteReturn(String path, List<String> params) throws IOException {
        BufferedReader br = null;
        params.add(0,path);
        Process process = new ProcessBuilder(params).start();
        br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        String result = "";
        while(true){
            try{
                if(((line = br.readLine()) != null)){
                    result += line;
                }else{
                    break;
                }
            }catch(IOException e){

            }
        }
        return result;
    }
}
