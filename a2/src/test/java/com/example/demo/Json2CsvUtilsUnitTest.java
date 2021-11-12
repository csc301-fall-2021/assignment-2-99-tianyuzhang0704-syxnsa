package com.example.demo;

import java.util.*;
import java.util.ArrayList;

import org.json.JSONObject;
import com.example.demo.Json2CsvUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

public class Json2CsvUtilsUnitTest {
    
    @Test
    void Json2Csv_test() {
        Json2CsvUtils jsonUtils = new Json2CsvUtils();
        ArrayList<JSONObject> arr = new ArrayList();
        JSONObject json1 = new JSONObject();
        json1.put("param", "1");
        JSONObject json2 = new JSONObject();
        json2.put("param", "2");
        arr.add(json1);
        arr.add(json2);
        String str = arr.toString();
        jsonUtils.Json2Csv(str);
    }
}
