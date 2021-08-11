package com.everis.steps.Packaging;

import org.junit.Assert;

import com.everis.pages.ProductMatrixPage;

import io.cucumber.java.en.Then;

public class ProductMatrixSteps {
	ProductMatrixPage productMatrixPage;

	public ProductMatrixSteps() {
		productMatrixPage = new ProductMatrixPage();
	}
	
	@Then("^I check if they are all able in the Product Matrix$")
	public void verifyOptionInProductMatrix() throws Exception {
		Assert.assertTrue(productMatrixPage.verifyOptionInProductMatrix());
	}
}