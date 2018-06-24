package com.oba.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.oba.utilities.EMailTestResults;
import com.oba.utilities.ExtentReportLibrary;

public class TestBase {
//**WebDriver, Properties File, Log4J, ExtentReports etc will be initialized here

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public static com.oba.utilities.ExtentReportLibrary ExtRep;
	public static String browser;
	public static String reportName;
/*	
	public static ExtentReportLibrary reports;
	public static ExtentTest test;
	public static ExtentTest testInfo;
	public static ExtentHtmlReporter htmlReporter;
*/
	
	@BeforeSuite
	public void setUp() {
		FileReader propertyFile = null;
		try {
			propertyFile = new FileReader("LoginDetails.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties configProperty = new Properties();
		try {
			configProperty.load(propertyFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String browser = configProperty.getProperty("Browser");
		String url = configProperty.getProperty("URL");
		
		if (browser.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "gecko.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome Launched !!!");
		} else if (browser.equals("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver",
			System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		log.debug("Navigated to : " + url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
	}

	
	


	@AfterSuite
	public void tearDown() {
		System.out.println("Inside @AfterSuie, Emailed Report " + reportName);
		try {
			EMailTestResults.emailResults(reportName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Test Results has been emailed to the intended recipients...");
		
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test execution completed !!!");
	}
}
	
