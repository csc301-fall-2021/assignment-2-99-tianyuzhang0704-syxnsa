package com.example.demo;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

@SpringBootTest
public class TimeUnitTest {

    @Test
	void getDays_test() {
		Time time = new Time();
		String startTime = "1/1/2020";
		String endTime = "1/2/2021";
		List<String> days = time.getDays(startTime, endTime);
	}

}
