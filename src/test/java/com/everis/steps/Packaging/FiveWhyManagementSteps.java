package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.FiveWhyManagementPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class FiveWhyManagementSteps {
	FiveWhyManagementPage fiveWhyManagementPage;

	public FiveWhyManagementSteps() {
		fiveWhyManagementPage = new FiveWhyManagementPage();
	}

	@And("^I create and delete a Five Why$")
	public void createFiveWhy() throws Exception {
		Assert.assertTrue(fiveWhyManagementPage.createFiveWhy());
	}

	@Then("^I edit a Five Why$")
	public void editFiveWhy() throws Exception {
		Assert.assertTrue(fiveWhyManagementPage.editFiveWhy());
	}
	
	@Then("^I filter the line Five Whys$")
	public void filterFiveWhy() throws Exception {
		Assert.assertTrue(fiveWhyManagementPage.filterFiveWhy());
	}
}