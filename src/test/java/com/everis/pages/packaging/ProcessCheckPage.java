package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class ProcessCheckPage extends BasePage {

	public ProcessCheckPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}
    /**
     * Verify if the Process Check Screen is being displayed
     * @return
     * @throws Exception
     */
	public boolean verifyProcessisplayed() throws Exception {
		boolean isProcessDisplayed = isElementDisplayed(By.xpath("//span[normalize-space()='Checks de Processo']"));
		boolean isOrderNumberCorrect = isElementDisplayed(
				By.xpath("//*[normalize-space()='" + dataMap.get("OrderListOrderNumber") + "']"));
		
		if(isProcessDisplayed && isOrderNumberCorrect) {
			log("The Process Check Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Process Check Screen");
		return false;
	}
}