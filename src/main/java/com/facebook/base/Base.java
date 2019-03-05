package com.facebook.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public Properties prop;
	public static WebDriver driver;
	//Load properties file
	public Base(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/facebook/config/config.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Browser type URL selection
	public void initialization(){
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("Firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
}
