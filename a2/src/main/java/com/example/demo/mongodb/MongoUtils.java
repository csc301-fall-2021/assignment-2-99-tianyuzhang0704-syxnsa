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
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoUtils {
	private String dbUrl = "mongodb+srv://cRERQ6ZQmVZ7B0T4:cRERQ6ZQmVZ7B0T4@cluster0.rikfx.mongodb.net/covid19?retryWrites=true&w=majority";
	// private String dbUrl = "mongodb+srv://123:123@cluster0.os4ea.mongodb.net/covid19?retryWrites=true&w=majority";
	
	public MongoUtils() {
        super();
    }
	
	public ArrayList<JSONObject> query2json(String database, String collection, ArrayList<String> search, ArrayList<String> data, List<String> days, String returnData) {
		 MongoClient mongoClient = MongoClients.create(dbUrl);
		 MongoDatabase mgdb = mongoClient.getDatabase(database);
		 MongoCollection<Document> datacollection = mgdb.getCollection(collection);
		 BasicDBObject query = new BasicDBObject();
		 for(int i = 0; i < search.size(); i++) {
			 query.put(search.get(i), data.get(i));
		 }
		 MongoCursor<Document> cursor = datacollection.find(query).skip(0).iterator();
		 ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
//		 String result = "[";

		 while (cursor.hasNext()) {
			 Document temp = cursor.next();
			 JSONObject item = new JSONObject();
			 if(collection.equals("dailyreport")) {
				 String date = temp.getString("Date");
				 if(days.contains(date)) {
					 String var = temp.getString(returnData);
					 item.put(returnData, var);
				 }
				 item.put("Date", date);
			 }
			 if(collection.equals("timeseries")){
				 String type = temp.getString("Return_Data");
				 if(type.equals(returnData)) {
					 item.put("Return_Data", type);
					 for(String day: days) {
						 item.put(day, temp.getString(day));
					 }
				 }
			 }
			 arr.add(item);
		 }
		 return arr;
//         JSONObject json = new JSONObject();
//         json.put("result", arr);
//         return json;
	}
	
	public void insert(JSONObject json, int type, String variable, String collectionName) {
        try (MongoClient mongoClient = MongoClients.create(dbUrl)) {
            MongoDatabase userDB = mongoClient.getDatabase("covid19");
            MongoCollection<Document> collection = userDB.getCollection(collectionName);          
            Document doc = Document.parse(json.toString());
            collection.insertOne(doc);
        }
    }

	public void delete(String key, String value, String collectionName) {
		try (MongoClient mongoClient = MongoClients.create(dbUrl)) {
            MongoDatabase userDB = mongoClient.getDatabase("covid19");
			MongoCollection<Document> collection = userDB.getCollection(collectionName);
            Bson filter = new Document(key, value);
            collection.deleteMany(filter);
        }
	}
	
	
}
