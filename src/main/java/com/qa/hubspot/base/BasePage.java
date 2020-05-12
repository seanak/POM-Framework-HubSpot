package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	//public WebDriver driver; //for ThreadLocal and for failure reports
	public Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsmanager;
	
	public static ThreadLocal<WebDriver> tldriver= new ThreadLocal<WebDriver>();    //Threadlocal is use for parallel execution
	
	
	public static synchronized WebDriver getDriver(){
		return tldriver.get();
	}
	
	
	public WebDriver init_driver(String browserName) {
		
		highlightElement = prop.getProperty("highlight").equals("yes")? true:false;
		
		optionsmanager = new OptionsManager(prop);
		
		System.out.println("Browser name :- "+ browserName);
		 
			if(browserName.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup(); //System.setProperty("webdriver.chrome.driver", "C:\\googleChromeDriver\\chromedriver_win32new81\\chromedriver.exe");
			
			
			tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions())); 
			
			
			
		}else if (browserName.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			tldriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));//driver= new FirefoxDriver(optionsmanager.getFirefoxOptions());
			
			
			
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			
			
		}else {
			System.out.println(browserName +" is not found please pass the correct browser name");
		}
		 getDriver().manage().deleteAllCookies();
		 getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// driver.get();
		 getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		 return getDriver();
	}
	
	public Properties init_properties() {
		prop = new Properties();
		
		String path = ("C:\\Users\\seana\\New Workspace\\1AseleniumNewProjectApril20Pract\\"
				+ "src\\main\\java\\com\\qa\\hubspot\\config\\config.properties"); 
		
			
	/*	String path = null;
		String env = null;
		
		try {
			env = System.getProperty("env");
			
			if(env.equals("qa")) {
				path = "C:\\Users\\seana\\New Workspace\\1AseleniumNewProjectApril20Pract\\"
						+ "src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
			} else if(env.equals("stage")){
				path = "C:\\Users\\seana\\New Workspace\\1AseleniumNewProjectApril20Pract\\"
						+ "src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
			}
			
		} catch (Exception e) {
			path = "C:\\Users\\seana\\New Workspace\\1AseleniumNewProjectApril20Pract\\"
					+ "src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
			
			System.out.println("error in the env path please correct it from base file  ");
		}*/
		
		
		
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("some issue with cofig.prop please check and correct your config...");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	/*
	 * take screen shots method 
	 */
	
		public String getScreenshot(){
			File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			String path = "C:/Users/seana/New Workspace/1AseleniumNewProjectApril20Pract/screenshots"+System.currentTimeMillis()+".png";
			File destination = new File ( path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				System.out.println("Screenshot capture failed....(BaseaPage)");
			}
			
			return path;
		}
}
