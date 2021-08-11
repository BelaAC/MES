package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.FillersNotificationsPage;

import io.cucumber.java.en.Then;

public class FillersNotificationsSteps {
	FillersNotificationsPage fillersNotificationsPage;

	public FillersNotificationsSteps() {
		 fillersNotificationsPage = new FillersNotificationsPage();
	}
	
	
	@Then("^I verify if the Notification Screen is being displayed$")
	public void verifyNotificationsDisplayed() throws Exception {
		Assert.assertTrue(fillersNotificationsPage.verifyNotificationsDisplayed());
	}
}