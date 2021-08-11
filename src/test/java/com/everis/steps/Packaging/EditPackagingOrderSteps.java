package com.everis.steps.Packaging;

import com.everis.pages.packaging.EditPackagingOrderPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class EditPackagingOrderSteps {
	EditPackagingOrderPage editPackagingOrderPage;

	public EditPackagingOrderSteps() {
		editPackagingOrderPage = new EditPackagingOrderPage();
	}

	@And("^I insert a random number in Planned production count$")
	public void editPorductionCount() throws Exception {
		editPackagingOrderPage.editPorductionCount();
	}

	@Then("^I click the Delete Order button$")
	public void clickingDeletButton() throws Exception {
		editPackagingOrderPage.clickingDeletButton();
	}
}