package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.QualityPage;

import io.cucumber.java.en.Then;

public class QualitySteps {
	QualityPage qualityPage;

	public QualitySteps() {
		qualityPage = new QualityPage();
	}

	@Then("^I verify if the Quality Screen is being displayed$")
	public void verifyQualityisplayed() throws Exception {
		Assert.assertTrue(qualityPage.verifyQualityisplayed());
	}
}