package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	JavaScriptExeUtil jse;
	
	
	//constructor of this class
	
	public ElementUtil(WebDriver driver) {
		this.driver= driver;
		wait = new WebDriverWait(driver, AppConstants.defaultTimeOut);
		jse = new JavaScriptExeUtil(driver);
		
	}
	
	//we don't need to write BY locator so we are creating this method 
	//we are managing the error handling with this by creating try and catch block
	
	//Explicit wait 
	
		public boolean waitForElementPresent(By locator) {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		}
		
		public boolean waitForElementVisiable(By locator) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			return false;
			
		}
		
		
		public String waitForPageTitle(String title) {
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
			
		}
	

		public WebElement getElement(By locator) {
			WebElement element = null;
			try {	
				
	//if(waitForElementPresent(locator));//mostly this is use in mobile app testing but we're doing it individually where it needed
				
			 element = driver.findElement(locator);
			 
			if (highlightElement) {
				jse.flash(element);
				}
				
		
			}catch(Exception e){
				System.out.println("some exeption got occurerd while creating the web Element "+ locator);
				}
			return element;
		}
		
		
	
	public String getPageTitle() {
		try {
		return driver.getTitle();
		}catch(Exception e) {
			System.out.println("some Exception occured while getting the title of the page");
		}
		return null;
	}
	
	
	
	public void doClick(By locator) {
		try {
		getElement(locator).click();
		}catch(Exception e) {
			System.out.println("some Exception occured while clicking on the element "+ locator);
		}
		
	}
	
	public void doSendKeys(By locator,String value) {
		try {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}catch(Exception e ) {
			System.out.println("some Exception got occured while entering value to the fild "+ locator);
		}
	}
	
	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		}catch(Exception e) {
			System.out.println("some Exception occured while displying the element "+ locator);
		}
		return false;
		
	}
	
	public String getText(By locator) {
		try {
		return getElement(locator).getText();
		}catch(Exception e ) {
			System.out.println("some Exception occured while gotting text from webelement "+ locator);
		}
		return null;
	}
	
}