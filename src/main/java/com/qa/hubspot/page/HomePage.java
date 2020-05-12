package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementutil;
	
	// create selector / locator
	
	By header = By.cssSelector("h1.private-page__title"); 
	
	By creatdashbtn = By.xpath("//button[@class='uiButton private-button private-button-"
			+ "-secondary private-button--default private-button--non-link']");
	
	By mainContact = By.id("nav-primary-contacts-branch");
	
	By childContact = By.id("nav-secondary-contacts");
	
	By createContactButton = By.className("//button [@class='uiButton private-button private-button--primary private-button--"
			+ "default add-obj private-button--non-link'] ");
	
	
	
	
	//constructor for driver 
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(driver);
	}
	
	//actions 
	
	public String getHomePageTitle() {
		
		elementutil.waitForPageTitle(AppConstants.homePageTitle);
		//return driver.getTitle();
		return elementutil.getPageTitle();
	}
	
	public String verifyHeader() {
		elementutil.waitForPageTitle(AppConstants.homePageTitle);
		//System.out.println("header is :- "+ header);
		//return driver.findElement(header).getText();
		return elementutil.getText(header);
		
	}
	
	public boolean checkCreatDashbtn() {
		elementutil.waitForElementPresent(creatdashbtn);
		//return driver.findElement(creatdashbtn).isDisplayed();
		return elementutil.doIsDisplayed(creatdashbtn);
	}
	
	public void clickOnContact() {
		
		elementutil.waitForElementVisiable(mainContact);
		elementutil.doClick(mainContact);
		
		elementutil.waitForElementVisiable(childContact);
		elementutil.doClick(childContact);
	}
	
	public ContactsPage goToContactPage() {
		
		clickOnContact();
		//elementutil.waitForElementPresent(createContactButton);
		elementutil.waitForPageTitle(AppConstants.contactPageTitle);
		return new ContactsPage(driver);
		
	}

}
