package com.everis.steps.brewing;

import org.junit.Assert;

import com.everis.pages.brewing.MaterialsPage;
import com.everis.pages.brewing.TanksPage;
import com.everis.pages.packaging.CreatePackagingOrderPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TanksSteps {
	TanksPage tanksPage;
	CreatePackagingOrderPage createPackagingOrderPage;
	MaterialsPage materialsPage;

	public TanksSteps() {
		tanksPage = new TanksPage();
		createPackagingOrderPage = new CreatePackagingOrderPage();
		materialsPage = new MaterialsPage();
	}

	@And("^I create a yeast sample for \"(.*)\"$")
	public void createYeastSample(String process) throws Exception {
		Assert.assertTrue(tanksPage.createYeastSample(process));
	}

	@And("^I transfer yeast recovery$")
	public void transferYeastRecovery() throws Exception {
		tanksPage.transferYeastRecovery();
	}

	@And("^I transfer yeast recovery to maturation$")
	public void transferYeastRecoveryToMaturation() throws Exception {
		tanksPage.transferYeastRecoveryToMaturation();
	}

	@And("^I crop yeast$")
	public void cropYeast() throws Exception {
		tanksPage.cropYeast();
		materialsPage.materialManagemntFermentationTub();
	}

	@And("^I spent yeast$")
	public void spentYeast() throws Exception {
		tanksPage.spentYeast();
	}

	@And("^I spent and check spent yeast$")
	public void spentAndCheckYeast() throws Exception {
		tanksPage.spentAndCheckYeast();
	}

	@And("^I transfer from maturation to filtration$")
	public void transferFromMaturationToFiltration() throws Exception {
		tanksPage.transferFromMaturationToFiltration();
		materialsPage.materialManagementFiltration();
	}

	@And("^I transfer from \"(.*)\" to filtration$")
	public void transferFromProcessToFiltration(String process) throws Exception {
		tanksPage.transferFromProcessToFiltration(process);
	}

	@And("^I transfer from tank to tank$")
	public void transferTankToTank() throws Exception {
		tanksPage.transferTankToTank();
	}

	@And("^I transfer to re-filtration$")
	public void transferToRefiltration() throws Exception {
		createPackagingOrderPage.openOrderForTransferFromBrewing();
		tanksPage.transferToRefiltration();
	}

	@Then("^I transfer out$")
	public void transferOut() throws Exception {
		tanksPage.transferOut();
	}

	@Then("^I transfer to packaging$")
	public void transferToPackaging() throws Exception {
		tanksPage.transferToPackaging();
	}

	@Then("^I transfer to TPs$")
	public void transferToTPs() throws Exception {
		tanksPage.transferToTPs();
	}

	@Then("^I transfer Yeast pitching to fermentation and unitank$")
	public void transferYeastPitchingToFermentationAndUnitank() throws Exception {
		tanksPage.transferYeastPitchingToFermentationAndUnitank();
	}

}