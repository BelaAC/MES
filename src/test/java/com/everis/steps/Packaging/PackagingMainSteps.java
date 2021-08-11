package com.everis.steps.Packaging;

import com.everis.pages.packaging.PackagingMainPage;

import io.cucumber.java.en.And;

public class PackagingMainSteps {
	PackagingMainPage packagingMainPage;

	public PackagingMainSteps() {
		packagingMainPage = new PackagingMainPage();
	}
	
	@And("^I check the Packaging area$")
	public void checkPackagingTranslated() throws Exception {
		packagingMainPage.checkPackagingTranslated();
	}

	@And("^I select the line L501$")
	public void selectLine501() throws Exception {
		packagingMainPage.selectLine501();
	}

	@And("^I select the line L505$")
	public void selectLine505() throws Exception {
		packagingMainPage.selectLine505();
	}

	@And("^I select the line L506$")
	public void selectLine506() throws Exception {
		packagingMainPage.selectLine506();
	}
	
	@And("^I select the line L521$")
	public void selectLine521() throws Exception {
		packagingMainPage.selectLine521();
	}
	
	@And("^I select the line L511$")
	public void selectLine511() throws Exception {
		packagingMainPage.selectLine511();
	}
	
	@And("^I select the line L536$")
	public void selectLine536() throws Exception {
		packagingMainPage.selectLine536();
	}
	
	@And("^I select the Line L501 Flow$")
	public void selectLine501Flow() throws Exception {
		packagingMainPage.selectLine501Flow();
	}
	
	@And("^I select the Line L505 Flow$")
	public void selectLine505Flow() throws Exception {
		packagingMainPage.selectLine505Flow();
	}
	
	@And("^I select the Line L506 Flow$")
	public void selectLine506Flow() throws Exception {
		packagingMainPage.selectLine506Flow();
	}
	
	@And("^I select the Line L521 Flow$")
	public void selectLine521Flow() throws Exception {
		packagingMainPage.selectLine521Flow();
	}
	
	@And("^I select the Line L511 Flow$")
	public void selectLine511Flow() throws Exception {
		packagingMainPage.selectLine511Flow();
	}
	
	@And("^I select the Line L536 Flow$")
	public void selectLine536Flow() throws Exception {
		packagingMainPage.selectLine536Flow();
	}
	
	@And("^I select the Line L501 Uptime$")
	public void selectLine501Uptime() throws Exception {
		packagingMainPage.selectLine501Uptime();
	}
	
	@And("^I select the Line L505 Uptime$")
	public void selectLine505Uptime() throws Exception {
		packagingMainPage.selectLine505Uptime();
	}
	
	@And("^I select the Line L506 Uptime$")
	public void selectLine506Uptime() throws Exception {
		packagingMainPage.selectLine506Uptime();
	}
	
	@And("^I select the Line L511 Uptime$")
	public void selectLine511Uptime() throws Exception {
		packagingMainPage.selectLine511Uptime();
	}
	
	@And("^I select the Line L521 Uptime$")
	public void selectLine521Uptime() throws Exception {
		packagingMainPage.selectLine521Uptime();
	}
	
	@And("^I select the Line L536 Uptime$")
	public void selectLine536Uptime() throws Exception {
		packagingMainPage.selectLine536Uptime();
	}
	
	@And("^I select Orders List View$")
	public void selectOrdersListView() throws Exception {
		packagingMainPage.selectOrdersListView();
	}
	
	@And("^I select Five Whys$")
	public void selectFiveWhys() throws Exception {
		packagingMainPage.selectFiveWhys();
	}
}