package com.kodcu.question06;

import java.util.HashMap;
import java.util.Map;

public class Changer {

    Map<Integer, String> map = new HashMap<Integer, String>();

    // ..
    public void addNode(int id, String newValue) {

        String value = map.get(id);
        if (value == null) {
            map.put(id, newValue);
        }
    }

   public boolean addNodeRight(int id, String newValue) {

           String value = map.get(id);
           if (value == null) {
               map.put(id, newValue);
               return true;
           }

           return false;
       }


}
