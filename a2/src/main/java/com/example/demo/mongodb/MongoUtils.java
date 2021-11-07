package com.example.demo.mongodb;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;

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
	
	public String query2json(String database, String collection, ArrayList<String> search, ArrayList<String> data, List<String> days) {
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
		 return result;
	}
	
	
}
