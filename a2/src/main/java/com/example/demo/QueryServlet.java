package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mongodb.MongoUtils;


@RestController

public class QueryServlet {
	@PostMapping("/query")
	@CrossOrigin(origins = "*",maxAge = 3600)
	public Object doPost(@RequestBody QueryForm queryform) throws IOException, ServletException {
		MongoUtils database = new MongoUtils();
		Time time = new Time();
		int filetype = queryform.getType();
		ArrayList<String> search = queryform.getSearch();
		ArrayList<String> data = queryform.getData();
		String startTime = queryform.getStartTime();
		String endTime = queryform.getEndTime();
		int returntype = queryform.getReturnType();
		List<String> days = time.getDays(startTime, endTime);
		System.out.println(days);
		String type;
		if(filetype == 0) {
			type = "dailyreport";
		}
		else {
			type = "timeseries";
		}
		if(returntype == 0) {
			return database.query2json("covid19", type, search, data, days);
		}
		return "";
		
	}

	public static class QueryForm{
		int filetype; // 0 for daily, 1 for time series
		ArrayList<String> search;
		ArrayList<String> data;
		String startTime;
		String endTime;
		int returntype; // 0 for json, 1 for csv
		
		public int getType() {
			return filetype;
		}
		
		public int getReturnType() {
			return returntype;
		}
		
		public ArrayList<String> getSearch() {
			return search;
		}
		
		public ArrayList<String> getData() {
			return data;
		}
		
		public String getStartTime() {
			return startTime;
		}
		
		public String getEndTime() {
			return endTime;
		}
		
		
		
	}
}
