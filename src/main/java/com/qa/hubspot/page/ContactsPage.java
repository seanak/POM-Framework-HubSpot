package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptExeUtil;

import io.qameta.allure.Step;

public class ContactsPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementutil;
	JavaScriptExeUtil jse;
	
	
	//create By locator
	
	By contactheader = By.xpath("//h1[@class='private-header__heading']");
	By createContactButton = By.xpath("//button[@class='uiButton private-button private-button--primary private-button--default add-obj private-button--non-link']/span/span");
	
	
	By email = By.xpath("//input[@data-field='email']");
	
	By firstName = By.xpath("//input[@data-field='firstname']");
	
	By lastName = By.xpath("//input[@data-field='lastname']");
	
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	
	By creteContactFormButton = By.xpath("//button [@class='uiButton private-button private-button--primary private-button--default private-loading-button private-button--primary private-button--non-link']/span");
	
	//class constructor 
	public ContactsPage (WebDriver driver) {
		
		this.driver= driver;
		elementutil = new ElementUtil(driver);
		jse = new JavaScriptExeUtil(driver);
		
	}
	
	//methods 

	public String getContactPageTitle() {
	
		elementutil.waitForPageTitle(AppConstants.contactPageTitle);
		return elementutil.getPageTitle();
	}
	
	public boolean checkCreateContBtn(){
		elementutil.waitForElementVisiable(contactheader);
		return elementutil.doIsDisplayed(createContactButton);
	}
	
	@Step("Create new contact with {0},{1},{2},{3}")// this will print in allure report parameters in packages
	public void createNewContact(String mail, String fn, String ln, String jobtitle) {
		elementutil.waitForElementPresent(createContactButton);
		elementutil.doClick(createContactButton);
		elementutil.waitForElementPresent(email);
		elementutil.doSendKeys(email, mail);
		elementutil.doSendKeys(firstName, fn);
		elementutil.doSendKeys(lastName, ln);
		elementutil.doSendKeys(jobTitle, jobtitle);
		//elementutil.doClick(creteContactFormButton);
		jse.clickElementByJS(elementutil.getElement(creteContactFormButton));
		
	}
	
	
}
