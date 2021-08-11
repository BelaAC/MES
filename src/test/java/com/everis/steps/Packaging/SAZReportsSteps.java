package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.SAZReportsPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class SAZReportsSteps {
	SAZReportsPage sazReportsPage;

	public SAZReportsSteps() {
		sazReportsPage = new SAZReportsPage();
	}

	@And("^I select the option \"(.*)\"$")
	public void selectReportOption(String reportOption) throws Exception {
		sazReportsPage.selectReportOption(reportOption);
	}
	
	@Then("^I export the Reports$")
	public void exportReports() throws Exception {
		sazReportsPage.exportReports();
	}
	
	@Then("^I export the Report$")
	public void exportStopAndProductionReport() throws Exception {
		Assert.assertTrue(sazReportsPage.exportStopAndProductionReport());
	}
}