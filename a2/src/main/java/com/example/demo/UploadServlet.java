package com.example.demo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mongodb.MongoUtils;

import au.com.bytecode.opencsv.CSVReader;

@RestController


public class UploadServlet{
	@PostMapping(path = "/upload")
	@CrossOrigin(origins = "*",maxAge = 3600)
	
	public Object doPost(@RequestParam String input, @RequestParam int filetype, @RequestPart(value = "file",required = false) MultipartFile file) throws IOException, ServletException {
		MongoUtils database = new MongoUtils();
		Csv2JsonUtils csvUtils = new Csv2JsonUtils();

		DataInputStream dataInputStream = new DataInputStream(file.getInputStream());
		CSVReader csvReader = new CSVReader(new InputStreamReader(dataInputStream));
		String[] header = csvReader.readNext();
		List<String[]> stringsList = csvReader.readAll();
		csvReader.close();
		JSONObject[] jsons = null;
		if(filetype == 0) {
			jsons = csvUtils.csv2JSON(header, stringsList, "Date", input);
			for(int i = 0; i < jsons.length; i++) {
				database.insert(jsons[i], filetype, input);
			}
		}
		if(filetype == 1) {
			jsons = csvUtils.csv2JSON(header, stringsList, "Return_Data", input);
			for(int i = 0; i < jsons.length; i++) {
				database.insert(jsons[i], filetype, input);
			}
		}
		
		
		return "Upload Success.";
	}
	
}
