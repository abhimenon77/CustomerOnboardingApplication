package com.oba.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.Status;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.oba.testbase.TestBase;

public class MongoDB_Methods extends TestBase {
	public static MongoClient mongoClient;
	public static DB db;
	public static DBCollection coll;

	@SuppressWarnings("deprecation")
	public static DB connectToDB(String hostName, String dbName) throws Exception {
		mongoClient = new MongoClient(hostName, 27017);
		db = mongoClient.getDB(dbName);
		ExtRep.testInfo.log(Status.INFO, "Connected to Database " + dbName + " on Host " + hostName);
		return db;
	}

	public static DBCollection validateImportedExcel(DB db, String tableName) {
		getExcelData(); //Assuming Excel file is grouped by Customer ID
		getDB_Data();
		return coll;
	}
	
	public static DBCollection getExcelData() {
		//Fetching the data from Excel and storing it in a Collections
		return coll;
		
	}
	
	public static DBCollection getDB_Data() {
		//Fetching the data from MongoDB and storing it in a Collections
		return coll;
		
	}
	
	
	public static DBCollection importFromExcelToDB(DB db, String tableName) {
		coll = db.getCollection(tableName);
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		return coll;
	}

	private static DBCollection selectAllRecordByCustomerID(DBCollection coll) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("customerId", 5);
		DBCursor cursor = coll.find(whereQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		return coll;
	}
	
	public static void importExcelDataToCustomerTable(String excelFile, DB db) {
		
		
	}

}
