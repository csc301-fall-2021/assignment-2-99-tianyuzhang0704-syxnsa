package com.example.demo;

import com.example.demo.QueryServlet;

import org.junit.Test;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
      mvc.perform( MockMvcRequestBuilders
          .post("/query")
          .content(asJsonString(new EmployeeVO(null, "firstName4", "lastName4", "email4@mail.com")))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }



}