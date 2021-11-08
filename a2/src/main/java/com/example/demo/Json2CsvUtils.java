package com.example.demo;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

public class Json2CsvUtils {
	
	public String Json2Csv(String json) throws JSONException {
		JSONArray jsonArray = new JSONArray(json);
		String csv =CDL.toString(jsonArray);  
		return csv;	
	}
}
