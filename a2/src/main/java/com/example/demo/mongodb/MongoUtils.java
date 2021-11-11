package com.example.demo.mongodb;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
					 item.put("Date", date);
				 }
				 
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

			 if(item.length() != 0) {
				 arr.add(item);
			 }	 
		 }
		 return arr;
//         JSONObject json = new JSONObject();
//         json.put("result", arr);
//         return json;
	}
	
	public void insert(JSONObject json, int type, String collectionName) {
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
	
	public ArrayList<JSONObject> queryForActive(String database, String collection, ArrayList<String> search, ArrayList<String> data, List<String> days){
		MongoClient mongoClient = MongoClients.create(dbUrl);
		MongoDatabase mgdb = mongoClient.getDatabase(database);
		MongoCollection<Document> datacollection = mgdb.getCollection(collection);
		BasicDBObject query = new BasicDBObject();
		for(int i = 0; i < search.size(); i++) {
			query.put(search.get(i), data.get(i));
		}
		MongoCursor<Document> cursor = datacollection.find(query).skip(0).iterator();
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		Hashtable<String, ArrayList<Integer>> confirmed = new Hashtable<String, ArrayList<Integer>>();
		Hashtable<String, ArrayList<Integer>> deaths = new Hashtable<String, ArrayList<Integer>>();
		Hashtable<String, ArrayList<Integer>> recovered = new Hashtable<String, ArrayList<Integer>>();
		
		while (cursor.hasNext()) {
			Document temp = cursor.next();
			String type = temp.getString("Return_Data");
			if(type.equals("Confirmed")) {
				for(String day: days) {
					if(!confirmed.containsKey(day)) {
						confirmed.put(day, new ArrayList<Integer>());
						confirmed.get(day).add(Integer.parseInt(temp.getString(day)));
					}
					else {
						confirmed.get(day).add(Integer.parseInt(temp.getString(day)));
					}			
				 }
			}
			if(type.equals("Deaths")) {
				for(String day: days) {
					if(!deaths.containsKey(day)) {
						deaths.put(day, new ArrayList<Integer>());
						deaths.get(day).add(Integer.parseInt(temp.getString(day)));
					}
					else {
						deaths.get(day).add(Integer.parseInt(temp.getString(day)));
					}		
				}
			}
			if(type.equals("Recovered")) {
				for(String day: days) {
					if(!recovered.containsKey(day)) {
						recovered.put(day, new ArrayList<Integer>());
						recovered.get(day).add(Integer.parseInt(temp.getString(day)));

					}
					else {
						recovered.get(day).add(Integer.parseInt(temp.getString(day)));
						}
				}
			}
		

		}

		if(confirmed.size() != deaths.size() || confirmed.size() != recovered.size() || deaths.size() != recovered.size()) {
			JSONObject error = new JSONObject();
			error.put("error", "some data is missing.");
			arr.add(error);
			return arr;
		}

		for (int i = 0; i < confirmed.get(days.get(0)).size(); i++) {
			JSONObject item = new JSONObject();
			item.put("Return_Data", "Active");
			for (String day:days) {
				Integer active = confirmed.get(day).get(i) - deaths.get(day).get(i) - recovered.get(day).get(i);			
				item.put(day, active.toString());
			}	
			if(item.length() != 0) {
				arr.add(item);
			}	
		}
		return arr;
	}	
	
}
