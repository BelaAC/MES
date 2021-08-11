package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.MaterialsPage;

import io.cucumber.java.en.Then;

public class MaterialsSteps {
	MaterialsPage materialsPage;

	public MaterialsSteps() {
		materialsPage = new MaterialsPage();
	}

	@Then("^I verify if the Materials Screen is being displayed$")
	public void verifyMaterialsisplayed() throws Exception {
		Assert.assertTrue(materialsPage.verifyMaterialsisplayed());
	}
}