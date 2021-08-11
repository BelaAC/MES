package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class MaterialsPage extends BasePage {

	public MaterialsPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}
    /**
     * Verify if the Materials Screen is being displayed
     * @return
     * @throws Exception
     */
	public boolean verifyMaterialsisplayed() throws Exception {
		boolean isMaterialsDisplayed = isElementDisplayed(By.xpath("//span[normalize-space()='Materiais']"));
		boolean isOrderNumberCorrect = isElementDisplayed(
				By.xpath("//*[normalize-space()='" + dataMap.get("OrderListOrderNumber") + "']"));
		
		if(isMaterialsDisplayed && isOrderNumberCorrect) {
			log("The Materials Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Materials Screen");
		return false;
	}
}