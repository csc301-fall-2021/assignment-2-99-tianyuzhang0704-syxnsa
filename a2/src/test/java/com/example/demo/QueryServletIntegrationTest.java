package com.example.demo;

import java.util.*;

import com.example.demo.QueryServlet;
import com.example.demo.QueryServlet.QueryForm;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.View;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.mock.web.MockHttpServletRequest.setContent;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;

import org.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QueryServlet.class)
public class QueryServletIntegrationTest {

    // @Mock
    // HttpServletRequest request;

    // @Mock
    // HttpServletResponse response;

    private QueryServlet queryServlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        queryServlet = new QueryServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testQuery() throws IOException, ServletException {

        // HttpServletRequest request = mock(HttpServletRequest.class);       
        // HttpServletResponse response = mock(HttpServletResponse.class);

        Time time = new Time();
        String startTime = "1/1/2021";
        String endTime = "2/1/2021";
        List<String> days = time.getDays(startTime, endTime);
        ArrayList<String> search = new ArrayList<String>(Arrays.asList("Province_State"));
        ArrayList<String> data = new ArrayList<String>(Arrays.asList("Alabama"));
        QueryForm qf = new QueryForm();
        qf.setSearch(search);
        qf.setData(data);
        qf.setStart(startTime);
        qf.setEnd(endTime);
        qf.setReturnData("Confirmed");
        qf.setFileType("0");
        qf.setType("0");

        ArrayList<String> search_param = new ArrayList();
        search_param.add("Province_State");
        ArrayList<String> data_param = new ArrayList();
        data_param.add("US");

        // request.addParameter("search", search_param.toString());
        // request.addParameter("data", data_param.toString());
        // request.addParameter("fileType", "0");
        // request.addParameter("startTime", "1/1/2020");
        // request.addParameter("endTime", "1/2/2021");
        // request.addParameter("type", "1");
        // request.addParameter("returnData", "Active");

        request.setContentType("application/json");
        request.setContent(qf.getBytes(StandardCharsets.UTF_8));

        queryServlet.doPost(request);
 
        // when(request.getParameter("search")).thenReturn(search_param.toString());
        // when(request.getParameter("dara")).thenReturn(data_param.toString());
        // when(request.getParameter("fileType")).thenReturn("0");
        // when(request.getParameter("startTime")).thenReturn("1/1/2020");
        // when(request.getParameter("endTime")).thenReturn("1/2/2021");
        // when(request.getParameter("type")).thenReturn("1");
        // when(request.getParameter("returnData")).thenReturn("Active");
 
        // StringWriter stringWriter = new StringWriter();
        // PrintWriter writer = new PrintWriter(stringWriter);
        // when(response.getWriter()).thenReturn(writer);
 
        // QueryServlet queryServlet = new QueryServlet();
        // queryServlet.doPost(request, response);
 
    }

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
          .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists());
    }



}