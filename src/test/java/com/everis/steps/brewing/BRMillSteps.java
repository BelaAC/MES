package com.everis.steps.brewing;

import org.junit.Assert;

import com.everis.pages.brewing.BHMillPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class BRMillSteps {
	BHMillPage bHMillPage;

	public BRMillSteps() {
		bHMillPage = new BHMillPage();
	}

	@And("^I open a new batch$")
	public void newBatch() throws Exception {
		bHMillPage.checkRunnigOrders();
	}

	@And("^I open CIP batches in \"(.*)\"$")
	public void newCIPBatch(String processArea) throws Exception {
		bHMillPage.newCIPBatch(processArea);
	}
	
	@And("^I edit the batch$")
	public void editBatch() throws Exception {
		Assert.assertTrue(bHMillPage.editBatch());
	}

	@And("^I start the batch$")
	public void startBatch() throws Exception {
		Assert.assertTrue(bHMillPage.startBatch());
	}

	@And("^I go to the \"(.*)\" BlendTool$")
	public void goToBlendTool(String processArea) throws Exception {
		bHMillPage.goToBlendTool(processArea);
	}

	@And("^I select two batches$")
	public void selectBatchesBlendTool() throws Exception {
		bHMillPage.selectBatchesBlendTool();
	}

	@Then("^I close the batch$")
	public void closeBatch() throws Exception {
		Assert.assertTrue(bHMillPage.closeBatch());
	}

	@Then("^I cancel the batch$")
	public void cancelBatch() throws Exception {
		Assert.assertTrue(bHMillPage.cancelBatch());
	}

	@Then("^I transfer out for every equipment$")
	public void transferOutAllEquipments() throws Exception {
		Assert.assertTrue(bHMillPage.transferOutAllEquipments());
	}

	@Then("^I should see the Blend$")
	public void verifyBlend() throws Exception {
		Assert.assertTrue(bHMillPage.verifyBlend());
	}
}