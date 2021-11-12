package com.example.demo;

import java.util.*;

import org.json.JSONObject;
import com.example.demo.QueryServlet;
import com.example.demo.QueryServlet.QueryForm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

public class QueryServletUnitTest {
    
    @Test
    void test_query_form() {
        Time time = new Time();
        String startTime = "1/1/2021";
        String endTime = "2/1/2021";
        List<String> days = time.getDays(startTime, endTime);
        ArrayList<String> search = new ArrayList<String>(Arrays.asList("Province_State"));
        ArrayList<String> data = new ArrayList<String>(Arrays.asList("Alabama"));
        QueryForm qf = new QueryForm();
        qf.setSearch(search);
        ArrayList<String> searchStored = qf.getSearch();
        assertEquals(search, searchStored, "setSearch works");
        qf.setData(data);
        ArrayList<String> dataStored = qf.getData();
        assertEquals(data, dataStored, "setData works");
        qf.setStart(startTime);
        String startStored = qf.getStartTime();
        assertEquals(startTime, startStored, "setStart works");
        qf.setEnd(endTime);
        String endStored = qf.getEndTime();
        assertEquals(endTime, endStored, "setEnd works");
        qf.setReturnData("Confirmed");
        String returnStored = qf.getReturnData();
        assertEquals("Confirmed", returnStored, "setReturnData works");
        qf.setFileType("0");
        String fileStored = qf.getFileType();
        assertEquals("0", fileStored, "setFileType works");
        qf.setType("0");
        String typeStored = qf.getType();
        assertEquals("0", typeStored, "setType works");
    }

}
