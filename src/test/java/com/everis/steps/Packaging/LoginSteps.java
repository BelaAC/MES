package com.everis.steps.Packaging;

import com.everis.pages.BasePage;
import com.everis.pages.LoginPage;
import com.everis.util.TestRule;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginSteps {

	@Given("^I have launched the website$")
	public void launchWebsite() {
		TestRule.openApplicationChrome("http://172.18.188.25/TS/Account/LogOn.aspx");
	}

	@And("^I login as High Priority ADM in \"(.*)\"$")
	public void loginAsHighPriorityADM(String moduleButton) throws Exception {
		LoginPage loginPage = new LoginPage();
		loginPage.loginAsHighPriorityADM(moduleButton);
	}
	
	@And("^I login as Medium Priority ADM in \"(.*)\"$")
	public void loginAsMediumPriorityADM(String moduleButton) throws Exception {
		LoginPage loginPage = new LoginPage();
		loginPage.loginAsMediumPriorityADM(moduleButton);
	}
	
	@And("^I login as Low Priority ADM in \"(.*)\"$")
	public void loginAsLowPriorityADM(String moduleButton) throws Exception {
		LoginPage loginPage = new LoginPage();
		loginPage.loginAsLowPriorityADM(moduleButton);
	}
	
	@And("^I login as ADM in Configuration$")
	public void loginAsADMinConfiguration() throws Exception {
		LoginPage loginPage = new LoginPage();
		loginPage.loginAsADMinConfiguration();
	}
	
	@And("^I login as the translated user$")
	public void loginAsTranslatedUser() throws Exception {
		LoginPage loginPage = new LoginPage();
		loginPage.loginAsTranslatedUser();
	}
	
	@And("^I login as ADM in SAZ Reports$")
	public void loginAsADMinSAZReports() throws Exception {
		LoginPage loginPage = new LoginPage();
		loginPage.loginAsADMinSAZReports();
	}

	@And("^I click the button \"(.*)\"$")
	public void clickButton(String button) throws Exception {
		BasePage basePage = new BasePage();
		basePage.clickButton(button);
	}
	
	
}