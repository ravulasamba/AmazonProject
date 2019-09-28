package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.amazon.Base.BasePage;
import com.qa.amazon.util.Constants1;
import com.qa.amazon.util.ElementUtil;
import com.qa.amazon.util.JavaScriptUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By signInTab = By.id("nav-link-accountList");
	By btnSignOut = By.id("nav-item-signout");
	By linkNewReleases = By.xpath("//*[text()='New Releases']");
	By textNewReleases = By.id("zg_banner_text_wrapper");
	
	//constructor of page class:
			public HomePage(WebDriver driver){
				this.driver = driver;
				elementUtil = new ElementUtil(driver);
			}
			
			public String getPageTitle(String title) {
				return elementUtil.waitForPageTitle(title);
			}
			
			public LoginPage clickSignOut(){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				elementUtil.waitForElementPresent(signInTab);
				elementUtil.doClick(signInTab);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				elementUtil.waitForElementPresent(btnSignOut);
				elementUtil.doClick(btnSignOut);
				return new LoginPage(driver);
			}

			public void clickNewReleasesLink(){
				elementUtil.doClick(linkNewReleases);
			}
			
			public String getNewReleasesText(){
				
				return elementUtil.doGetText(textNewReleases);
			}
}
