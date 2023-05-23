package com.liu.callable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Map<String,String>> {
    @Override
    public Map<String, String> call() throws Exception {

        Map<String,String> res =  new LinkedHashMap<>();
        res.put("name","liu");
        res.put("age","20");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }

        return res;
    }
}
