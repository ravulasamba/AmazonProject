package com.qa.amazon.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author Sambasiva
 *
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static String flash;
		
	public static final String USERNAME = "sambasiva4";
	public static final String AUTOMATE_KEY = "M6xoMedSbYujfKwDdtqy";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	/**
	 * This method is used to initialize the driver on the basis of given
	 * browser
	 * 
	 * @return this method returns webdriver instance
	 * @throws MalformedURLException 
	 */
	public WebDriver initialize_driver(Properties prop,String browser1) throws MalformedURLException {
		// String browser = "chrome";
		String browser = browser1;
		String headless = prop.getProperty("headless");
		flash = prop.getProperty("elementflash");

		if (browser.equalsIgnoreCase("chrome")) {
			
			//browserstack
		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "Chrome");
		    caps.setCapability("browser_version", "77.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability("resolution", "1024x768");
		    caps.setCapability("name", "Amazon Sample Test");
		    /////
		    
		    //WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
			if (headless.equalsIgnoreCase("yes")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
				//driver = new RemoteWebDriver(new URL(URL), caps);
			}
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver.exe");
			//WebDriverManager.firefoxdriver().setup();
			if (headless.equalsIgnoreCase("yes")) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			} else {
				driver = new FirefoxDriver();
			}
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

		return driver;
	}

	/**
	 * This method is used to define the properties
	 * 
	 * @return this method returns properties prop reference
	 */
	public Properties initialize_properties() {

		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/amazon/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("some exception occurred while quitting the browser");
		}
	}

	public void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("some exception occurred while closing the browser");
		}
	}
	

}
