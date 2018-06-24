package com.oba.testcases;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.oba.utilities.EMailTestResults;
import com.oba.utilities.ExtentReportLibrary;

public class ExtentReportTest {
	ExtentReportLibrary ExtRep;
	public static String reportName;

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
		ExtRep.captureStatus(result);
	}
	
	@AfterTest
	public void cleanUp() {
		ExtRep.cleanUp();
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		System.out.println("Inside @AfterSuite, Emailed Report " + reportName);
		EMailTestResults.emailResults(reportName);

	}
	
	
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
	
	
}
