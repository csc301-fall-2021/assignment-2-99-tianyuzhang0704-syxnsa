package com.example.demo;

import com.example.demo.mongodb.MongoUtils;
import com.example.demo.entity.DailyReport;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A2ApplicationTests {

	@Test
	void mongodbTest() {
		MongoUtils mongoUtils = new MongoUtils();

		Time time = new Time();
		String startTime = "01/01/2020";
		String endTime = "01/02/2021";
		List<String> days = time.getDays(startTime, endTime);
		ArrayList<String> search = new ArrayList<String>(Arrays.asList("Province_State"));
		ArrayList<String> data = new ArrayList<String>(Arrays.asList("Alabama"));
		// mongoUtils.query2json("covid19", "dailyreport", search, data, days);
	}

	@Test
	void dailyReportTest() {
		DailyReport dailyReport = new DailyReport("Alabama", "US", "100", "10", "5", "5", "01/01/2021");

		dailyReport.setProvince("California");
		assertEquals("California", dailyReport.Province_State, "setProvince works");
		dailyReport.setCountry("Canada");
		assertEquals("Canada", dailyReport.Country_Region, "setCountry works");
		dailyReport.setConfirmed("200");
		assertEquals("200", dailyReport.Confirmed, "setConfirmed works");
		dailyReport.setDeaths("0");
		assertEquals("0", dailyReport.Deaths, "setDeaths works");
		dailyReport.setRecovered("30");
		assertEquals("30", dailyReport.Recovered, "setRecovered works");
		dailyReport.setActive("10");
		assertEquals("10", dailyReport.Active, "setActive works");
	}

}
   