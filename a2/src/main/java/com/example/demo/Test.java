package com.example.demo;

import org.json.JSONObject;

import au.com.bytecode.opencsv.CSVReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
 
public class Test {
    public static void main(String[] args) {
        Csv2JsonUtils csvUtils = new Csv2JsonUtils();
 
       
      
        
        JSONObject[] jsons = null;
 
        try {
   
            
            File file = new File("C:\\Document\\COVID-19\\csse_covid_19_data\\csse_covid_19_daily_reports_us\\test.csv");
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            CSVReader csvReader = new CSVReader(new InputStreamReader(dataInputStream));
            String[] keys = csvReader.readNext();
            List<String[]> list = csvReader.readAll();
            jsons = csvUtils.csv2JSON(keys, list, "Date", "2020-01-01");
            System.out.println(jsons[0]);

//            for (JSONObject json : jsons) {
//                for (int i = 0; i < keys.length; i++) {
//                    System.out.println("key:" + keys[i] + "  value:" + json.getString(keys[i]));
//                }
//                System.out.println("key:" + "Date" + " value:" + "2020-01-01");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



