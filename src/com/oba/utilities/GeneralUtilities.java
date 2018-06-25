package com.oba.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class GeneralUtilities {
	public static WebDriver openWebPage(WebDriver driver, String URL){
		driver.get(URL);
		System.out.println("Opened the WebPage " + URL);
		return driver;
	}
	
	
	public static String getValueOf(String fileName, String Key) throws IOException{
		System.out.println("<*** Inside getValueOf ****>");
		System.out.println("File Name :" + fileName + ", Key : " + Key);
		FileReader file = new FileReader(fileName);
		Properties prop = new Properties();
		prop.load(file);
		String keyValue = prop.getProperty(Key);
		System.out.println(keyValue);
		
		
		return keyValue;
	}
	
	
	public static String tokenizeTime(String timeStamp) throws Exception{
		System.out.println("Inside tokenizeTime " + timeStamp);
		String year = timeStamp.substring(0, 4);
		String month = timeStamp.substring(4, 6);
		String day = timeStamp.substring(6, 8);
		
		String tIME = timeStamp.substring(9, 15);
		String time1 = tIME.substring(0, 2) + "-" + tIME.substring(2, 4) + "-" + tIME.substring(4, 6);
		System.out.println(month + "~" + day + "~" + year + "_" + time1);
		
		return(month + "~" + day + "~" + year + "_" + time1);
	}

}


