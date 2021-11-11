package com.example.demo;

import com.example.demo.QueryServlet;

import org.junit.Test;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.View;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QueryServlet.class)
public class A2IntegrationTest {

    @Autowired
    private MockMvc mvc;

    // @Autowired
    // private ObjectMapper objectMapper;

    // @Autowired
    // private EmployeeRepository repository;


    @Test
    public void createQueryAPI() throws Exception 
    {
      JSONObject query_param = new JSONObject();
      ArrayList<String> search_arr = new ArrayList();
      search_arr.add("Country_Region");
      query_param.put("search", search_arr);
      ArrayList<String> data_arr = new ArrayList();
      data_arr.add("US");
      query_param.put("data", data_arr);
      query_param.put("fileType", "0");
      query_param.put("startTime", "1/1/2020");
      query_param.put("endTime", "1/2/2021");
      query_param.put("type", "0");
      query_param.put("returnData", "Active");
      mvc.perform( MockMvcRequestBuilders
          .post("/query")
          .content(query_param.toString())
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }



}