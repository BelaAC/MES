package com.everis.steps.Packaging;

import com.everis.pages.packaging.LineFlowPage;

import io.cucumber.java.en.Then;

public class LineFlowSteps {
	LineFlowPage lineFlowPage;

	public LineFlowSteps() {
		lineFlowPage = new LineFlowPage();
	}

	@Then("^I verify if all the disconnected TAGs are disconnected$")
	public void verifyTAGS() throws Exception {
		lineFlowPage.verifyTAGS();
	}
}