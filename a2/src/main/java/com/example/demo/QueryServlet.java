package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONObject;
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
		String fileType = queryform.getFileType();
		ArrayList<String> search = queryform.getSearch();
		ArrayList<String> data = queryform.getData();
		String startTime = queryform.getStartTime();
		String endTime = queryform.getEndTime();
		String returnType = queryform.getType();
		String returnData = queryform.getReturnData();
		List<String> days = time.getDays(startTime, endTime);
		String type;
		if(fileType.equals("0")) {
			type = "dailyreport";
			System.out.println(type.equals("dailyreport"));
		}
		else {
			type = "timeseries";
		}
		ArrayList<JSONObject> arr = database.query2json("covid19", type, search, data, days, returnData);
		if(returnType.equals("0")) {
	        JSONObject result = new JSONObject();
	        result.put("result", arr);
			return result.toString();
		}
		if(returnType.equals("1")) {
			Json2CsvUtils jsonUtils = new Json2CsvUtils();
			String json = arr.toString();
	    	String csv = jsonUtils.Json2Csv(json);
	    	JSONObject result = new JSONObject();
	    	result.put("CSV", csv);
	    	return result.toString();
		}
		return "";
		
	}

	public static class QueryForm{
		ArrayList<String> search;
		ArrayList<String> data;
		String startTime;
		String endTime;
		String returnData;
		String fileType;
		String type;
		
		
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
		
		public String getReturnData() {
			return returnData;
		}
		
		public String getFileType() {
			return fileType;
		}
		
		public String getType() {
			return type;
		}
		
		public void setSearch(ArrayList<String> search) {
			this.search = search;
		}
		
		public void setData(ArrayList<String> data) {
			this.data = data;
		}
		
		public void setStart(String start) {
			this.startTime = start;
		}
		public void setEnd(String end) {
			this.endTime = end;
		}
		public void setReturnData(String data) {
			this.returnData = data;
		}
		public void setFileType(String type) {
			this.fileType = type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
	}
}
