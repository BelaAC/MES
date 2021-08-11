package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.CategorizeEventPage;
import com.everis.pages.packaging.DashboardPage;
import com.everis.pages.packaging.DeleteEventPage;
import com.everis.pages.packaging.EventListShiftHistoryPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class EventListShiftHistorySteps {
	EventListShiftHistoryPage eventListShiftHistoryPage;
	DashboardPage dashboardPage;
	DeleteEventPage deleteEventPage;
	CategorizeEventPage categorizeEventPage;

	public EventListShiftHistorySteps() {
		eventListShiftHistoryPage = new EventListShiftHistoryPage();
		dashboardPage = new DashboardPage();
		deleteEventPage = new DeleteEventPage();
		categorizeEventPage = new CategorizeEventPage();
	}

	@Then("^I verify if the Event List for Shift History Screen is being displayed$")
	public void verifyEventListShiftScreen() throws Exception {
		Assert.assertTrue(eventListShiftHistoryPage.verifyEventListShiftDisplayed());
	}

	@And("^I split a Event")
	public void splitEvent() throws Exception {
		eventListShiftHistoryPage.selectPossibleEventSplit();
	}

	@And("^I categorize a Event")
	public void categorizeEvent() throws Exception {
		eventListShiftHistoryPage.categorizeEvent();
	}

	@And("^I verify if each radiobutton Events table is being displayed$")
	public void varifyAllRadioButtons() throws Exception {
		eventListShiftHistoryPage.varifyAllRadioButtons();
	}

	@And("^I select the first possible Event to Split$")
	public void selectPossibleEventSplit() throws Exception {
		eventListShiftHistoryPage.selectPossibleEventSplit();
	}

	@And("^I select the start date as last month$")
	public void selectFilterLastMonth() throws Exception {
		dashboardPage.clickEventsButton();
		eventListShiftHistoryPage.selectFilterLastMonth();
	}

	@And("^I select the first possible Event to Delete$")
	public void selectPossibleEventDelete() throws Exception {
		eventListShiftHistoryPage.selectPossibleEventDelete();
	}

	@And("^I select the first possible Event to Categorize$")
	public void selectPossibleEventCategorize() throws Exception {
		eventListShiftHistoryPage.selectPossibleEventCategorize();
	}

	@And("^I select the first possible Event to Make Changes$")
	public void selectPossibleEventChanges() throws Exception {
		eventListShiftHistoryPage.selectPossibleEventChanges();
	}

	@And("^I multi categorize$")
	public void selectTwoPossibleEventsCategorize() throws Exception {
		dashboardPage.clickEventsButton();
		eventListShiftHistoryPage.selectTwoPossibleEventsCategorize();
		categorizeEventPage.selectRandomCategoryAndDefinition();
		Assert.assertTrue(eventListShiftHistoryPage.verifySpecificationForEvents());
	}

	@Then("^I verify if the specifications for the event are correct$")
	public void verifySpecificationForEvent() throws Exception {
		Assert.assertTrue(eventListShiftHistoryPage.verifySpecificationForEvent());
	}

	@Then("^I verify in the Notes if surplus character were excluded$")
	public void verifyNotesChanges() throws Exception {
		Assert.assertTrue(eventListShiftHistoryPage.verifyNotesChanges());
	}

	@Then("^I verify if the specifications for the events are correct$")
	public void verifySpecificationForEvents() throws Exception {
		Assert.assertTrue(eventListShiftHistoryPage.verifySpecificationForEvents());
	}

	@Then("^I verify if the \"(.*)\" Events table is being displayed$")
	public void verifyEventsTable(String eventsTable) throws Exception {
		Assert.assertTrue(eventListShiftHistoryPage.verifyEventsTable(eventsTable));
	}

	@Then("^I multi delete two events$")
	public void selectTwoPossibleEvent() throws Exception {
		eventListShiftHistoryPage.selectTwoPossibleEvent();
		deleteEventPage.deleteEvents();
	}

	@And("^I verify if the Event was splitted$")
	public void verifySplittedEvent() throws Exception {
		Assert.assertTrue(eventListShiftHistoryPage.verifySplittedEvent());
	}
}