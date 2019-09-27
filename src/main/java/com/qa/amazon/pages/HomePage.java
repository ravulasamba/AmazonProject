package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.amazon.Base.BasePage;
import com.qa.amazon.util.ElementUtil;
import com.qa.amazon.util.Constants;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By signInTab = By.id("nav-link-accountList");
	By btnSignOut = By.id("nav-item-signout");
	
	//constructor of page class:
			public HomePage(WebDriver driver){
				this.driver = driver;
				elementUtil = new ElementUtil(driver);
			}
			
			public String getHomePageTitle() {
				return elementUtil.waitForPageTitle(Constants.HOME_PAGE_TITLE);
			}
			
			public LoginPage clickSignOut(){
				elementUtil.waitForElementPresent(signInTab);
				elementUtil.doClick(signInTab);
				elementUtil.waitForElementPresent(btnSignOut);
				elementUtil.doClick(btnSignOut);
				return new LoginPage(driver);
			}

}
