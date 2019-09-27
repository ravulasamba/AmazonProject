package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.amazon.Base.BasePage;
import com.qa.amazon.util.ElementUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By emailId = By.id("ap_email");
	By continues = By.id("continue");
	By password = By.id("ap_password");
	By loginButton = By.id("signInSubmit");

	//constructor of page class:
		public LoginPage(WebDriver driver){
			this.driver = driver;
			elementUtil = new ElementUtil(driver);
		}
		
		public HomePage doLogin(String username, String pwd) throws InterruptedException{
			elementUtil.doSendKeys(emailId, username);
			elementUtil.doClick(continues);
			Thread.sleep(3000);
			elementUtil.doSendKeys(password, pwd);
			elementUtil.doClick(loginButton);
			
			return new HomePage(driver);
		}
		
}
