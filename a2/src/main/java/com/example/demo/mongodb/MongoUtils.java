package com.example.demo.mongodb;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoUtils {
	private String dbUrl = "mongodb+srv://cRERQ6ZQmVZ7B0T4:cRERQ6ZQmVZ7B0T4@cluster0.rikfx.mongodb.net/covid19?retryWrites=true&w=majority";
	
	public MongoUtils() {
        super();
    }
	
	public JSONObject query2json(String database, String collection, ArrayList<String> search, ArrayList<String> data, List<String> days) {
		 MongoClient mongoClient = MongoClients.create(dbUrl);
		 MongoDatabase mgdb = mongoClient.getDatabase(database);
		 MongoCollection<Document> datacollection = mgdb.getCollection(collection);
		 BasicDBObject query = new BasicDBObject();
		 for(int i = 0; i < search.size(); i++) {
			 query.put(search.get(i), data.get(i));
		 }
		 MongoCursor<Document> cursor = datacollection.find(query).skip(0).iterator();
		 String result = "[";
		 while (cursor.hasNext()) {
			 String jsonString = cursor.next().toJson();
			 String str1 = jsonString.substring(0, jsonString.indexOf("Date\": \""));
			 String date = jsonString.substring(str1.length() + 8, jsonString.length() - 2);
			 if(days.contains(date)) {
				 result += jsonString;
				 result += ",";
			 }
		 }
		 result = result.substring(0, result.length() - 1);
         result += "]";
         JSONObject json = new JSONObject();
         json.put(result, false)
	}
	
	public void insert(JSONObject json, int type, String variable) {
        try (MongoClient mongoClient = MongoClients.create(dbUrl)) {
            MongoDatabase userDB = mongoClient.getDatabase("covid19");
            MongoCollection<Document> collection;
            if(type == 0) {
            	collection = userDB.getCollection("dailyreport");
            	BasicDBObject query = new BasicDBObject();
            	String province = json.getString("Province_State");
            	System.out.println(province);
//            	String jsonString = json.toString();
//            	int index1 = jsonString.indexOf("Province_State") + 17;
//            	int index2 = jsonString.indexOf("\"", index1);
//            	String province = jsonString.substring(index1, index2);

            	query.put("Date", variable);
            	query.put("Province_State", province);
            	collection.findOneAndDelete(query);
            }
            else {
            	collection = userDB.getCollection("timeseries");
            }
            
            
            Document doc = Document.parse(json.toString());
            collection.insertOne(doc);
         
        }
    }
	
	
}
