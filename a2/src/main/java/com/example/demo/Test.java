package com.example.demo;

import org.json.JSONObject;
import java.util.List;
 
public class Test {
    public static void main(String[] args) {
        Csv2JsonUtils csvUtils = new Csv2JsonUtils();
 
       
        String[] keys = new String[6];
        keys[0]="Province_State";
        keys[1]="Country_Region";
        keys[2]="Confirmed";
        keys[3]="Deaths"; 
        keys[4]="Recovered";
        keys[5]="Active";
        
        JSONObject[] jsons = null;
 
        try {
   
            List<String[]> list = csvUtils.readCSV("C:\\Document\\COVID-19\\csse_covid_19_data\\csse_covid_19_daily_reports_us\\test.csv");
            jsons = csvUtils.csv2JSON(keys, list);
 

            for (JSONObject json : jsons
            ) {
                System.out.println("----------------");
                for (int i = 0; i < keys.length; i++) {
                    System.out.println("key:" + keys[i] + "  value:" + json.getString(keys[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}