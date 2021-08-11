package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.DashboardPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class DashboardSteps {
	DashboardPage dashboardPage;

	public DashboardSteps() {
		dashboardPage = new DashboardPage();
	}
	
	@Then("^I verify the started order$")
	public void verifyStartedOrder() throws Exception {
		Assert.assertTrue(dashboardPage.verifyStartedOrder());
	}
	
	@And("^I capture the running Order Number$")
	public void caputureRunningOrderNumber() throws Exception {
		dashboardPage.caputureRunningOrderNumber();
	}
	
	@And("^I select the Events button$")
	public void clickEventsButton() throws Exception {
		dashboardPage.clickEventsButton();
	}
	
	@And("^I select the PTPs \"(.*)\" button$")
	public void clickQualityButton(String ptpButton) throws Exception {
		dashboardPage.clickPTPButton(ptpButton);
	}
	
	@Then("^I verify if the order was ended$")
	public void verifyEndedOrder() throws Exception {
		Assert.assertTrue(dashboardPage.verifyEndedOrder());
	}
	
	@Then("^I select the first Old Order$")
	public void oldOrderRecords() throws Exception {
		Assert.assertTrue(dashboardPage.oldOrderRecords());
	}
}