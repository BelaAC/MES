package com.everis.steps.brewing;

import com.everis.pages.brewing.MaterialsPage;

import io.cucumber.java.en.And;

public class MaterialsSteps {
	MaterialsPage materialsPage;

	public MaterialsSteps() {
		materialsPage = new MaterialsPage();
	}

	@And("^I add, edit and cancel a material$")
	public void addMaterial() throws Exception {
		materialsPage.materialsManagement();
	}

}