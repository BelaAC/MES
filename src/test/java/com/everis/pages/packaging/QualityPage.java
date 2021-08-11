package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class QualityPage extends BasePage {

	public QualityPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Verify if the Quality Screen is being displayed
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyQualityisplayed() throws Exception {
		boolean isQualityDisplayed = isElementDisplayed(By.xpath("//span[normalize-space()='Qualidade']"));
		boolean isOrderNumberCorrect = isElementDisplayed(
				By.xpath("//*[normalize-space()='" + dataMap.get("OrderListOrderNumber") + "']"));
		
		if (isQualityDisplayed && isOrderNumberCorrect) {
			log("The Quality Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Quality Screen");
		return false;
	}
}