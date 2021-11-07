package com.example.demo.mongodb;

import java.util.ArrayList;

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
	
	public String query2json(String database, String collection, ArrayList<String> search, ArrayList<String> data) {
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
             result += jsonString;
             result += ",";
		 }
		 result = result.substring(0, result.length() - 1);
         result += "]";
         System.out.println(result);
		 return result;
	}
	
}
