package com.qa.hubspot.tests;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listeners.TestAllureListeners;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic - 101 : create login page features")
@Feature("US - 501 : create test on login page Hub Spot")
@Listeners({TestAllureListeners.class})

public class LoginPageTest {
	
	//create ref names 
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	Credentials usercrd;
	
	
	@BeforeMethod(alwaysRun= true)
	@Parameters(value = {"browser"})
	public void setUp(String browser) {
		
		String browsername=null;
		
	  basepage = new BasePage();
	  prop =  basepage.init_properties();
	  
	  
	  
	  if(browser.equals(null)){
	  browsername= prop.getProperty("browser");
	  }else{
		  browsername = browser;
	  }
	  
	  driver = basepage.init_driver(browsername);
	  driver.get(prop.getProperty("url"));
	  loginpage = new LoginPage(driver);
	  usercrd = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	             
	  }
	
	
	
	@Test(priority = 1, description = "verify LoginPageTitle_test ")// this description from testNg 
	@Description("verify LoginPageTitle_test ")// this description from Allure 
	@Severity( SeverityLevel.NORMAL)
	public void verifyLoginPageTitle_test() {
		String logtitle = loginpage.getPageTitle();
		System.out.println("login page title is :- " + logtitle);
		assertEquals(logtitle,AppConstants.loginPageTitle );
	}
	
	
	
	
	@Test (priority = 2)
	@Description("verify check SingnUp link_test ")
	@Severity( SeverityLevel.MINOR)
	public void checkSignuplink_Test() {
		Assert.assertTrue(loginpage.checkSingnUplink());
		
	}
	
/*
	
	@DataProvider
	public Object[][] getInvalidData() {
		Object data[][] = {
							{"test123@gmail.com", " "},
							{"test567@gmail.com", "testA678"},
							{"test9789@gmail.com", "test§123"},
							{" ", "test§123"},
							{"  ", "  "}
							};
		return data;
	}
	//negative test case
		@Test (priority = 4, dataProvider = "getInvalidData", enabled = false)
		public void login_invalidTestCases(String username, String pwd) throws InterruptedException {
			usercrd.setAppUsername(username);
			usercrd.setAppPassword(pwd);
			loginpage.doLogin(usercrd);
			
			//Assert.assertEquals(homepage.getHomePageTitle(), prop.getProperty("homePageTitle"));
		}
*/
	
		@Test (priority = 3, description= "login test with correct username and correct password...")
		@Description("login in to home page_Test ")
		@Severity( SeverityLevel.BLOCKER)
		public void doLogin_Test() {
			HomePage homepage = loginpage.doLogin(usercrd);
			String homepagetitle = homepage.getHomePageTitle();
			Assert.assertEquals(homepagetitle , AppConstants.homePageTitle);
		}
	
	
	@AfterMethod(alwaysRun= true)
	public void teardown() {
		driver.quit();
	}

}
