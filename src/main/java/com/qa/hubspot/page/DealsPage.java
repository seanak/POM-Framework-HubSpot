package com.qa.hubspot.page;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class DealsPage extends BasePage {
	
	WebDriver driver;
	Properties prop;
	JavascriptExecutor jse;
	ElementUtil elementutil;
	
	// create By locators
	By header = By.xpath("//h1/i18n-string");
	
	//create constructor 
	public DealsPage(WebDriver driver){
		this.driver= driver;
		elementutil = new ElementUtil(this.driver);
		
	}
	
	//create methods 
	
	public String getDealsPageTittle(){
		String dealspageTitle= elementutil.getPageTitle();
		return dealspageTitle;
		
	}
	
	public String getPageHeader(){
		WebElement dPheader= elementutil.getElement(header);
		return dPheader.getText();
	}
	
	
}
