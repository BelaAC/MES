package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.packaging.PlannedOrdersPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PlannedOrdersSteps {
	PlannedOrdersPage plannedOrdersPage;

	public PlannedOrdersSteps() {
		plannedOrdersPage = new PlannedOrdersPage();
	}

	@And("^I should see the Order Number and all others specifications$")
	public void checkingOrderNumberAndSpecifications() throws Exception {
		Assert.assertTrue(plannedOrdersPage.checkingOrderNumberAndSpecifications());
	}
	
	@And("^I check the changed specifications$")
	public void checkingChangedSpecifications() throws Exception {
		Assert.assertTrue(plannedOrdersPage.checkingChangedSpecifications());
	}
	
	@And("^I should not see the Order Number$")
	public void checkingOrderNumber() throws Exception {
		Assert.assertTrue(plannedOrdersPage.checkingDeletedOrder());
	}
	
	@And("^I filter the last month orders$")
	public void filterLastMonthOrders() throws Exception {
		plannedOrdersPage.filterLastMonthOrders();
	}
	
	@And("^I select a random Product$")
	public void selectRandomProduct() throws Exception {
		plannedOrdersPage.selectRandomProduct();
	}
	
	@Then("^I verify if all planned orders are displayed$")
	public void verifyLastMonthPlannedOrders() throws Exception {
		plannedOrdersPage.verifyLastMonthPlannedOrders();
	}
	
	@Then("^I verify if only planned orders referring to the chosen product are shown$")
	public void verifyProductOrders() throws Exception {
		Assert.assertTrue(plannedOrdersPage.verifyProductOrders());
	}
}