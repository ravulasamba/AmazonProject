package com.qa.amazon.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentBase {
	
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}

}
