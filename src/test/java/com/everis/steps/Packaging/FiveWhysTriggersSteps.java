package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.FiveWhysTriggersPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class FiveWhysTriggersSteps {
	FiveWhysTriggersPage fiveWhysTriggersPage;

	public FiveWhysTriggersSteps() {
		fiveWhysTriggersPage = new FiveWhysTriggersPage();
	}
	
	@And("^I select the first System ordem for L501$")
	public void selectFirstSystemL501() throws Exception {
		fiveWhysTriggersPage.selectFirstSystemL501();
	}
	
	@And("^I select the first System ordem for L505$")
	public void selectFirstSystemL505() throws Exception {
		fiveWhysTriggersPage.selectFirstSystemL505();
	}
	
	@And("^I select the first System ordem for L506$")
	public void selectFirstSystemL506() throws Exception {
		fiveWhysTriggersPage.selectFirstSystemL506();
	}
	
	@And("^I select the first System ordem for L521$")
	public void selectFirstSystemL521() throws Exception {
		fiveWhysTriggersPage.selectFirstSystemL521();
	}
	
	@And("^I select the first System ordem for L511$")
	public void selectFirstSystemL511() throws Exception {
		fiveWhysTriggersPage.selectFirstSystemL511();
	}
	
	@And("^I select the first System ordem for L536$")
	public void selectFirstSystemL536() throws Exception {
		fiveWhysTriggersPage.selectFirstSystemL536();
	}
	
	@And("^I insert a random number for the \"(.*)\"$")
	public void insertRandomNumberFiveWhy(String combobox) throws Exception {
		fiveWhysTriggersPage.insertRandomNumberFiveWhy(combobox);
	}
	
	@Then("^I verify if the Edited System Properties are correct$")
	public void verifySystemPropertiesEdit() throws Exception {
		Assert.assertTrue(fiveWhysTriggersPage.verifySystemPropertiesEdit());
	}
}