package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class PeriodicalPage extends BasePage {

	public PeriodicalPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}
    /**
     * Verify if the Periodical Screen is being displayed
     * @return
     * @throws Exception
     */
	public boolean verifyPeriodicalisplayed() throws Exception {
		boolean isPeriodicalDisplayed = isElementDisplayed(By.xpath("//span[normalize-space()='Periódico']"));
		
		if(isPeriodicalDisplayed) {
			log("The Periodical Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Periodical Screen");
		return false;
	}
}