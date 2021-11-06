package com.example.demo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import au.com.bytecode.opencsv.CSVReader;

public class Csv2JsonUtils {
	 
    /**
     * 
     * @return List<String[]>
     * @throws IOException
     */
    public List<String[]> readCSV(String csvName) throws IOException {
        File file = new File(csvName);
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        CSVReader csvReader = new CSVReader(new InputStreamReader(dataInputStream));
        List<String[]> stringsList = csvReader.readAll();
        return stringsList;
    }
 
 
    /**
     * 
     * @param 
     * @param
     * @return
     */
    public JSONObject csv2JSON(String[] keys,String[] values) throws JSONException {
 
        JSONObject json = new JSONObject();
        for (int i = 0; i < keys.length; i++) {
            try{
                json.append(keys[i],values[i]);
            }
            catch (ArrayIndexOutOfBoundsException e){
                json.append(keys[i],null);
            }
        }
        return json;
    }
 
    /**
     * 
     * @param 
     * @param 
     * @return
     */
    public JSONObject[] csv2JSON(String[] keys,List<String[]> stringsList) throws JSONException {
 
        JSONObject[] jsons = new JSONObject[stringsList.size()];
        int index = 0 ;
        for (String[] strings : stringsList
             ) {
            JSONObject json = this.csv2JSON(keys, strings);
            jsons[index] = json;
            index ++ ;
        }
        return jsons;
    }
}
