package com.qa.hubspot.tests;

import static org.testng.Assert.assertEquals;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listeners.TestAllureListeners;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 102 : create Home page features")
@Feature("US - 502 : create test on Home page Hub Spot, US - 503 : Using home page go to contact page ")
@Listeners({TestAllureListeners.class})

public class HomePageTest {
	
	//create ref.
	
	WebDriver driver;
	Properties prop;
	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;
	Credentials usercrd;
	ContactsPage contactpage;
	
	
	
	@BeforeTest
	
	public void setUp()  {
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
		
		
		}
	@Test(priority = 1)
	@Description("verify home Page Title_Test ")
	@Severity( SeverityLevel.TRIVIAL)
	public void homePageTitleTest() {
		System.out.println("home page title is :- "+ homepage.getHomePageTitle());
		assertEquals(homepage.getHomePageTitle(), AppConstants.homePageTitle);
	}
	
	@Test (priority = 2 )
	@Description("verify home Page Header_Test  ")
	@Severity( SeverityLevel.MINOR)
	public void verifyHeader_Test() {
		Assert.assertEquals(homepage.verifyHeader(),AppConstants.homePageHeader);
	
	}
	
	@Test (priority = 3)
	@Description("check Creat Dash button_test ")
	@Severity( SeverityLevel.MINOR)
	public void checkCreatDashbtn_Test() {
		Assert.assertTrue(homepage.checkCreatDashbtn());
	
	}
	
	@Test(priority = 4)
	@Description("verify check SingnUp link_test ")
	@Severity( SeverityLevel.CRITICAL)
	public void goToContactpage_test() {
		ContactsPage contactpage =homepage.goToContactPage();
		String contactpageTitle =contactpage.getContactPageTitle();
		Assert.assertEquals(contactpageTitle,AppConstants.contactPageTitle);
		
	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}
	

}
