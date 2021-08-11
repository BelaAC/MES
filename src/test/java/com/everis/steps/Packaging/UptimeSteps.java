package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.UptimePage;

import io.cucumber.java.en.Then;

public class UptimeSteps {
	UptimePage uptimePage;

	public UptimeSteps() {
		uptimePage = new UptimePage();
	}
	
	@Then("^I verify if all graphics are working$")
	public void UptimeGraphicsWorking() throws Exception {
		Assert.assertTrue(uptimePage.UptimeGraphicsWorking());
	}
}