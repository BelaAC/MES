package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class CockpitPage extends BasePage {

	public CockpitPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Verify if the Cockpit Screen is being displayed
	 * 
	 * @throws Exception
	 */
	public boolean verifyCockpitDisplayed() throws Exception {
		boolean isCockpitDisplayed = isElementDisplayed(By.xpath("//span[contains(., 'Dashboard - Ordem')]"));
		boolean isOrderNumberCorrect = isElementDisplayed(By.xpath("//span[normalize-space()='"+dataMap.get("OrderListOrderNumber")+"']"));
		
		if (isCockpitDisplayed && isOrderNumberCorrect) {
			log("The Cockpit Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Cockpit Screen");
		return false;
	}
}