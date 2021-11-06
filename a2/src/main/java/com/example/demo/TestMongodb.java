package com.example.demo;

/**
 * Hello world!
 *
 */
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class TestMongodb {

    public static void main(String args[]) {
    	String dbUrl = "mongodb+srv://cRERQ6ZQmVZ7B0T4:cRERQ6ZQmVZ7B0T4@cluster0.rikfx.mongodb.net/covid19?retryWrites=true&w=majority";

        try {

            // To connect to mongodb server
            MongoClient mongoClient = MongoClients.create(dbUrl);

            // Now connect to your databases
            MongoDatabase mgdb = mongoClient.getDatabase("covid19");

            System.out.println("Connect to database successfully!");
            System.out.println("MongoDatabase inof is : "+mgdb.getName());
            // If MongoDB in secure mode, authentication is required.
            // boolean auth = db.authenticate(myUserName, myPassword);
            // System.out.println("Authentication: "+auth);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}