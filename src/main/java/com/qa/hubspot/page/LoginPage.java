package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptExeUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementutil;
	JavaScriptExeUtil jse;
	 
	
	//by locators 
	
	By emailid = By.id("username");
	By password = By.id("password");
	By loginbutton = By.id("loginBtn");
	By signuplink = By.xpath("//*[@class='private-link uiLinkWithoutUnderline uiLinkDark m-left-1']");
	By errormess = By.xpath("//h5[@class='Heading-sc-9dtc71-0 H5-sc-1o270om-0 jaYosH private-alert__title']");
	By homePageheader = By.cssSelector("h1.private-page__title"); //this is just after login test to verify home page in wait for ele. visible 		
	
	
	// constructor for driver 
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			elementutil = new ElementUtil(driver);
			jse = new JavaScriptExeUtil(driver);
			
		}
	
	// page actions 
	
	public String getPageTitle() {
		elementutil.waitForPageTitle(AppConstants.loginPageTitle);
		return elementutil.getPageTitle();
	}
	
	
	public boolean checkSingnUplink() {
		elementutil.waitForElementPresent(signuplink);
		return elementutil.doIsDisplayed(signuplink);
			
		}
	
	public HomePage doLogin(Credentials usercrd){
		elementutil.waitForElementPresent(emailid);
		elementutil.doSendKeys(emailid, usercrd.getAppUsername());
		elementutil.doSendKeys(password, usercrd.getAppPassword());
		elementutil.doClick(loginbutton);
		elementutil.waitForElementVisiable(homePageheader);
		
		/*driver.findElement(emailid).sendKeys(username);;
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginbutton).click();*/
		
		return new HomePage(driver);
	}
}
