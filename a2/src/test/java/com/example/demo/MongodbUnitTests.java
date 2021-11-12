package com.example.demo;

import com.example.demo.mongodb.MongoUtils;

import java.util.*;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

@SpringBootTest
class MongodbUnitTests {

	@Test
	void query2json_test() {
		MongoUtils mongoUtils = new MongoUtils();

		Time time = new Time();
		String startTime = "01/01/2020";
		String endTime = "01/02/2021";
		List<String> days = time.getDays(startTime, endTime);
		ArrayList<String> search = new ArrayList<String>(Arrays.asList("Province_State"));
		ArrayList<String> data = new ArrayList<String>(Arrays.asList("Alabama"));
		mongoUtils.query2json("covid19", "dailyreport", search, data, days, "Active");
	}


	@Test
	void insert_test() {
		MongoUtils mongoUtils = new MongoUtils();
		// mongoUtils.insert();

	}

	@Test
	void delete_test() {
		MongoUtils mongoUtils = new MongoUtils();

		mongoUtils.delete("Date", "1/1/2021", "dailyreport");
	}

	@Test
	void queryForActive_test() {
		MongoUtils mongoUtils = new MongoUtils();

		Time time = new Time();
		String startTime = "1/20/2021";
		String endTime = "2/10/2021";
		List<String> days = time.getDays(startTime, endTime);
		ArrayList<String> search = new ArrayList<String>(Arrays.asList("Country/Region"));
		ArrayList<String> data = new ArrayList<String>(Arrays.asList("Afghanistan"));
		mongoUtils.queryForActive("covid19", "timeseries", search, data, days);
	}
}
   