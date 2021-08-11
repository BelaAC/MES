package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.CockpitPage;

import io.cucumber.java.en.Then;

public class CockpitSteps {
	CockpitPage cockpitPage;

	public CockpitSteps() {
		cockpitPage = new CockpitPage();
	}
	
	
	@Then("^I verify if the Cockpit Screen is being displayed$")
	public void verifyCockpitDisplayed() throws Exception {
		Assert.assertTrue(cockpitPage.verifyCockpitDisplayed());
	}
}