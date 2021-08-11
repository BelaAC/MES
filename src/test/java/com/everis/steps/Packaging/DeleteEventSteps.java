package com.everis.steps.Packaging;

import com.everis.pages.packaging.DeleteEventPage;

import io.cucumber.java.en.Then;

public class DeleteEventSteps {
	DeleteEventPage deleteEventPage;

	public DeleteEventSteps() {
		deleteEventPage = new DeleteEventPage();
	}

	@Then("^I delete the Event$")
	public void deleteEvent() throws Exception {
		deleteEventPage.deleteEvent();
	}
}