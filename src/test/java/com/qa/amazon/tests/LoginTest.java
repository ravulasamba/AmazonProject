package com.qa.amazon.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.amazon.Base.BasePage;
import com.qa.amazon.pages.HomePage;
import com.qa.amazon.pages.LandingPage;
import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.util.Constants1;
import com.qa.amazon.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends ExtentBase{
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LandingPage landingPage;
	LoginPage loginPage;
	HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws MalformedURLException {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop,browser);
		landingPage = new LandingPage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
	}
	
	@Test(priority = 1, description = "login test with correct username and correct password, check title and logout....")
	public void loginTest() throws InterruptedException {
		extentTest = extent.startTest("loginTest");
		if (landingPage.hoverOnSignIn()){
			landingPage.clickSignIn();
			loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
		else
			System.out.println(landingPage.hoverOnSignIn());
		
		String title = homePage.getPageTitle(Constants1.HOME_PAGE_TITLE);
		System.out.println(title);
		Assert.assertEquals(title, Constants1.HOME_PAGE_TITLE, "login page title is in correct...");
		
		homePage.clickSignOut();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		basePage.quitBrowser();
	}
	

}
