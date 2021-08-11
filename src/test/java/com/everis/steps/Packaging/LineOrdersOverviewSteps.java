package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.DashboardPage;
import com.everis.pages.packaging.LineOrdersOverviewPage;

import io.cucumber.java.en.Then;

public class LineOrdersOverviewSteps {
	LineOrdersOverviewPage lineOrdersOverviewPage;
	DashboardPage dashboardPage;

	public LineOrdersOverviewSteps() {
		lineOrdersOverviewPage = new LineOrdersOverviewPage();
		dashboardPage = new DashboardPage();
	}

	@Then("^I verify if the PTPs counter is correct$")
	public void verifyQualityRemaining() throws Exception {
		dashboardPage.clickPTPButton("PTPs da Ordem");
		Assert.assertTrue(lineOrdersOverviewPage.verifyQualityRemaining());
		dashboardPage.clickPTPButton("Periódico");
		Assert.assertTrue(lineOrdersOverviewPage.verifyQualityRemaining());
	}
}