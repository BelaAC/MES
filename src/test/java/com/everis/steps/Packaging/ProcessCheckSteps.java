package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.ProcessCheckPage;

import io.cucumber.java.en.Then;

public class ProcessCheckSteps {
	ProcessCheckPage processCheckPage;

	public ProcessCheckSteps() {
		processCheckPage = new ProcessCheckPage();
	}

	@Then("^I verify if the Process Check Screen is being displayed$")
	public void verifyProcessisplayed() throws Exception {
		Assert.assertTrue(processCheckPage.verifyProcessisplayed());
	}
}