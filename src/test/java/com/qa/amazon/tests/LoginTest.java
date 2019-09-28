package com.qa.amazon.tests;

import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.amazon.Base.BasePage;
import com.qa.amazon.pages.HomePage;
import com.qa.amazon.pages.LandingPage;
import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.util.Constants1;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginTest {
	
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
	@Description("login test with correct username and correct password, check title and logout....")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() throws InterruptedException {
		if (landingPage.hoverOnSignIn()){
			landingPage.clickSignIn();
			loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
		else
			System.out.println(landingPage.hoverOnSignIn());
		
		String title = homePage.getHomePageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants1.HOME_PAGE_TITLE, "login page title is in correct...");
		
		homePage.clickSignOut();
	}
	
//	@Test(priority = 1, description = "Home page title test....")
//	@Description("Home page title test....")
//	@Severity(SeverityLevel.NORMAL)
//	public void homePageTitleTest() {
//		String title = homePage.getHomePageTitle();
//		System.out.println(title);
//		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "login page title is in correct...");
//	}
	
	@AfterMethod
	public void tearDown() {
		basePage.quitBrowser();
	}

}
