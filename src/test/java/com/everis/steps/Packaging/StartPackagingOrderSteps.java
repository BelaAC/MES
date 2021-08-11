package com.everis.steps.Packaging;

import com.everis.pages.packaging.StartPackagingOrdePage;

import io.cucumber.java.en.And;

public class StartPackagingOrderSteps {
	StartPackagingOrdePage startPackagingOrdePage;

	public StartPackagingOrderSteps() {
		startPackagingOrdePage = new StartPackagingOrdePage();
	}

	@And("^I capture the Order Number in the Start Order Page$")
	public void captureOrderNumberInStartPage() throws Exception {
		startPackagingOrdePage.captureOrderNumberInStartPage();
	}
	
	@And("^I change the start date for yesterday$")
	public void changingStartDate() throws Exception {
		startPackagingOrdePage.changingStartDate();
	}
}