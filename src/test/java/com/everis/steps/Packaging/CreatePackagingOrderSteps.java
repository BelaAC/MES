package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.CreatePackagingOrderPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CreatePackagingOrderSteps {
	CreatePackagingOrderPage createPackagingOrderPage;

	public CreatePackagingOrderSteps() {
		createPackagingOrderPage = new CreatePackagingOrderPage();
	}

	@And("^I create a new Order$")
	public void createNewOrder() throws Exception {
		createPackagingOrderPage.createNewOrder();
	}

	@And("^I edit the Order$")
	public void editOrder() throws Exception {
		createPackagingOrderPage.editOrder();
	}

	@And("^I start the Order$")
	public void startOrder() throws Exception {
		Assert.assertTrue(createPackagingOrderPage.startOrder());
	}

	@Then("^I end the Order$")
	public void endOrder() throws Exception {
		Assert.assertTrue(createPackagingOrderPage.endOrder());
	}
	
	@Then("^I delete the Order$")
	public void deleteOrder() throws Exception {
		Assert.assertTrue(createPackagingOrderPage.deleteOrder());
	}
	
	@Then("^I cancel the Order$")
	public void cancelOrder() throws Exception {
		Assert.assertTrue(createPackagingOrderPage.cancelOrder());
	}

	@And("^I verify the possibles options for the line$")
	public void capturingpossiblesOptions() {
		createPackagingOrderPage.capturingpossiblesOptions();
	}
}