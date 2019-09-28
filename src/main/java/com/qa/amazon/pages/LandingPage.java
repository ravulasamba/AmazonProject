package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.amazon.Base.BasePage;
import com.qa.amazon.util.ElementUtil;

public class LandingPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By signInTab = By.id("nav-link-accountList");
	By btnSignIn = By.xpath("//span[@class='nav-action-inner' and text()='Sign in']");
	
	//constructor of page class:
	public LandingPage(WebDriver driver){
			this.driver = driver;
			elementUtil = new ElementUtil(driver);
	}
	
	public boolean hoverOnSignIn(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementUtil.waitForElementPresent(signInTab);
		elementUtil.doClick(signInTab);
		elementUtil.waitForElementPresent(btnSignIn);
		return elementUtil.isElementDisplayed(btnSignIn);
	}
	
	public LoginPage clickSignIn(){
		elementUtil.doClick(btnSignIn);
		return new LoginPage(driver);
	}

}
