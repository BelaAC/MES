package com.everis.steps.Packaging;

import com.everis.pages.ConfigurationSystemsPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ConfigurationSystemsSteps {
	ConfigurationSystemsPage configurationSystemsPage;

	public ConfigurationSystemsSteps() {
		configurationSystemsPage = new ConfigurationSystemsPage();
	}
	
	@And("^I go to the Line configurations$")
	public void goLineConfiguartions() throws Exception {
		configurationSystemsPage.goLineConfiguartions();
	}
	
	@Then("^I verify all the system \"(.*)\"$")
	public void selectSystem(String system) throws Exception {
		configurationSystemsPage.selectSystem(system);
	}
}