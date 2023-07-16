package org.util;

import java.util.*;

public class SortUtil {
    public static Map<String,String> sortMap(Map<String,String> map, boolean desc){
        Map<String,String> result = new HashMap<>();
        List<Map.Entry<String,String>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        if(desc){
            Collections.reverse(entryList);
        }
        for(Map.Entry<String,String> mapEntry : entryList){
            result.put(mapEntry.getKey(),mapEntry.getValue());
        }
        return result;
    }
}
