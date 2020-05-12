package com.qa.hubspot.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listeners.TestAllureListeners;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Epic - 103 : create Home page features")
@Feature("US - 504 : create test on Contacts page Hub Spot, US - 505 : Using Xcel file uploaded employee list in ContactPage Contacts")
@Listeners({TestAllureListeners.class})

public class ContactsPageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactpage;
	Credentials usercrd;

	@BeforeMethod
	public void setUp(){
		basepage = new BasePage();
		prop= basepage.init_properties();
		String browsername = prop.getProperty("browser");
		driver= basepage.init_driver(browsername);
		driver.get(prop.getProperty("url"));
		loginpage = new LoginPage(driver);
		 usercrd = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		loginpage.doLogin(usercrd);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		homepage = new HomePage(driver);
		contactpage =homepage.goToContactPage();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		contactpage = new ContactsPage(driver);
	}
	
	/*@Test(priority = 1)
	public void contactPageTitle_Test(){
		String contactpagetitle = contactpage.getContactPageTitle();
		Assert.assertEquals(contactpagetitle, AppConstants.contactPageTitle);
	}
	
	@Test (priority  = 2)
	public void checkCreatConBtn_Test(){
		Assert.assertTrue(contactpage.checkCreateContBtn());
	}*/
	
	@DataProvider
	public Object[][] getContactsTestData(){
		Object[][] data = ExcelUtil.getTestData("contacts");
		return data;
		
	}
	
	@Test (priority = 3, dataProvider= "getContactsTestData")
	public void createContacts_Test(String email, String firstname,String lastname, String jobtitle){//whatever you have in this para give same down
		contactpage.createNewContact(email,firstname,lastname,jobtitle);//from above same method para give same here
		
	}
	
	
	
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}
}
