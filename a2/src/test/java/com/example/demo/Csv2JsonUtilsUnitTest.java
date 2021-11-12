package com.example.demo;

import java.util.*;
import java.util.ArrayList;

import org.json.JSONObject;
import com.example.demo.Csv2JsonUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

public class Csv2JsonUtilsUnitTest {

    @Test
    void csv2JSON_test1() {
        Csv2JsonUtils csvUtils = new Csv2JsonUtils();

        String[] keys = {"key1", "key2"};
        String[] values = {"val1", "val2"};
        csvUtils.csv2JSON(keys, values, "Date", "1/1/2021");
    }

    @Test
    void csv2JSON_test2() {
        Csv2JsonUtils csvUtils = new Csv2JsonUtils();

        String[] keys = {"key1", "key2"};
        List<String[]> stringsList = new ArrayList();
        String[] values1 = {"val1", "val2"};
        String[] values2 = {"val1", "val2"};
        stringsList.add(values1);
        stringsList.add(values2);
        csvUtils.csv2JSON(keys, stringsList, "Date", "1/1/2021");
    }

}
