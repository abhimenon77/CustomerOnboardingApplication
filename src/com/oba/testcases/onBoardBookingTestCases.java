package com.oba.testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.oba.testbase.TestBase;
import com.oba.utilities.EMailTestResults;
import com.oba.utilities.ExtentReportLibrary;
import com.oba.utilities.GeneralUtilities;

public class onBoardBookingTestCases extends TestBase {
	ExtentReportLibrary ExtRep;
	
	public static WebDriver captureScreen;

	@BeforeTest
	public void setupExtentReports() throws Exception {
		ExtRep = new ExtentReportLibrary();
		System.out.println("Inside BeforeTest");
		reportName = ExtRep.setupExtentReports("Login_F_QA");
		System.out.println("----------------" + reportName);
	}
	
	@BeforeMethod
	public void register(Method method) {
		ExtRep.register(method);
	}
	
	@AfterMethod
	public void captureStatus(ITestResult result) {
		captureScreen = driver;
		ExtRep.captureStatus(result);
	}
	
	@AfterTest
	public void cleanUp() {
		ExtRep.cleanUp();
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		

	}
	
	
	@Test
	public void Login() throws Exception {
		System.out.println("Inside Test");
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test One.");
	}

	/*
	@Test
	public void testOne() throws Exception {
		System.out.println("Inside Test");
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test One.");
	}

	
	@Test
	public void testTwo() {
		Assert.assertTrue(false);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Two.");
	}
	
	
	@Test
	public void testThree() {
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Three.");
	}
	
	
	@Test
	public void testFour() {
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Four.");
	}
	
	*/
}
