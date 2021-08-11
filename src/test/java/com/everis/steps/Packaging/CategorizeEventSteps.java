package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.CategorizeEventPage;
import com.everis.pages.packaging.DashboardPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.pt.E;

public class CategorizeEventSteps {
	CategorizeEventPage categorizeEventPage;
	DashboardPage dashboardPage;

	public CategorizeEventSteps() {
		categorizeEventPage = new CategorizeEventPage();
		dashboardPage = new DashboardPage();
	}

	@And("^I create a New Downtime$")
	public void createNewDowntime() throws Exception {
		dashboardPage.clickEventsButton();
		categorizeEventPage.createNewDowntime();
	}

	@E("^I select random options for Event Category and Definition$")
	public void selectRandomCategoryAndDefinition() throws Exception {
		categorizeEventPage.selectRandomCategoryAndDefinition();
	}

	@And("^I select random options for System, Event Category and Definition$")
	public void selectRandomSystemCategoryAndDefinition() throws Exception {
		categorizeEventPage.selectRandomSystemCategoryAndDefinition();
	}

	@And("^I select the Event Category as \"(.*)\"$")
	public void selectEventCategory(String eventCategory) throws Exception {
		categorizeEventPage.selectEventCategory(eventCategory);
	}

	@Then("^I check if the Events Category options are correct$")
	public void eventsCategoryOptionsCorrect() throws Exception {
		Assert.assertTrue(categorizeEventPage.eventsCategoryOptionsCorrect());
	}

	@Then("^I check if all the events category are correct$")
	public void allEventsCategoryOptionsCorrect() throws Exception {
		categorizeEventPage.allEventsCategoryOptionsCorrect();
	}

	@Then("^I check if the Events Definition options are correct$")
	public void eventsDefinitionOptionsCorrect() throws Exception {
		Assert.assertTrue(categorizeEventPage.eventsDefinitionOptionsCorrect());
	}

	@Then("^I check if the filter is working$")
	public void eventsFilter() throws Exception {
		Assert.assertTrue(categorizeEventPage.eventsFilter());
	}
}