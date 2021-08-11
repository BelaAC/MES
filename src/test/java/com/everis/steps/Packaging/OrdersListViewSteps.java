package com.everis.steps.Packaging;

import com.everis.pages.packaging.OrdersListViewPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class OrdersListViewSteps {
	OrdersListViewPage ordersListViewPage;

	public OrdersListViewSteps() {
		ordersListViewPage = new OrdersListViewPage();
	}
	
	@And("^I select random values for criterias$")
	public void selectRandomCriterias() throws Exception {
		ordersListViewPage.selectRandomCriterias();
	}
	
	@And("^I filter the last week orders$")
	public void filterLastWeekOrders() throws Exception {
		ordersListViewPage.filterLastWeekOrders();
	}
	
	@Then("^I verify if all orders referring to the period of time are shown$")
	public void verifyLastWeekOrders() throws Exception {
		ordersListViewPage.verifyLastWeekOrders();
	}
	
	@Then("^I check if all PTPs options are working$")
	public void verifyPTPOption() throws Exception {
		ordersListViewPage.verifyPTPOption();
	}
}