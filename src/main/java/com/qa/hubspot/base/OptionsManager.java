package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	
	/* 
	 * for chromeOptions you can do so many things for that link 
	https://peter.sh/experiments/chromium-command-line-switches/
	*/
	
	public ChromeOptions co;
	public Properties prop;
	public FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
	  this.prop= prop;
	}
	
	public ChromeOptions getChromeOptions() {
		
		co= new ChromeOptions();
		
		if (prop.getProperty("incognito").equals("yes")) co.addArguments("--incognito");
		
		if (prop.getProperty("headless").equals("yes"))  co.addArguments("--headless");
		
		return co;
		
	}
	
	public FirefoxOptions getFirefoxOptions() {
		
		fo = new FirefoxOptions();
		
		if(prop.getProperty("headless").equals("yes")) fo.addArguments("--headless");
		
		return fo;
		
	}

}
