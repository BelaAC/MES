package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.PeriodicalPage;

import io.cucumber.java.en.Then;

public class PeriodicalSteps {
	PeriodicalPage periodicalPage;

	public PeriodicalSteps() {
		periodicalPage = new PeriodicalPage();
	}

	@Then("^I verify if the Periodical Screen is being displayed$")
	public void verifyPeriodicalisplayed() throws Exception {
		Assert.assertTrue(periodicalPage.verifyPeriodicalisplayed());
	}
}