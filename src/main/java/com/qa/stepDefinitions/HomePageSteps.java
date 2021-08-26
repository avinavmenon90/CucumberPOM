package com.qa.stepDefinitions;

import com.qa.pages.HomePage;
import com.qa.pages.Loginpage;
import com.qa.util.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class HomePageSteps extends TestBase{

	Loginpage loginPage;
	HomePage homePage;
	
	
	@Given("^User opens browser$")
	public void user_opens_browser() throws Throwable {

		TestBase.initialization(); //to execute initialization method in TestBase class
	  
	}

	@Then("^user is on login page$")
	public void user_is_on_login_page() {

		loginPage = new Loginpage(); //to initialize LoginPage object immediately and call the loginPage method
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals("CRMPRO Log In Screen", title);
	 
	}

	@Then("^user logs into app$")
	public void user_logs_into_app()  {

		//log in and initialize HomePage object (to call methods of HomePage)	
		homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password")); //read un, pw from config.properties file

	}


	@Then("^validate home page titile$")
	public void validate_home_page_titile()  {
		
		String homeTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals("CRMPRO", homeTitle);
	}

	@Then("^validate logged in username$")
	public void validate_logged_in_username() throws InterruptedException  {
		
		Thread.sleep(3000); //put 3s delay for Homepage and username to be loaded
		boolean flag = homePage.verifyCorrectUserName();
		Assert.assertTrue(flag); //confirm username is displayed
	
	}


	
}
