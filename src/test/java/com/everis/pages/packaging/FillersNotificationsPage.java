package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class FillersNotificationsPage extends BasePage {

	public FillersNotificationsPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Verify if the Filler/s Notifications Screen is being displayed
	 * 
	 * @throws Exception
	 */
	public boolean verifyNotificationsDisplayed() throws Exception {
		Select DrpBatchOrder = new Select(driver.findElement(By.id("SelectBatchOrder_Filter")));
		DrpBatchOrder.selectByVisibleText(dataMap.get("OrderListOrderNumber"));
		
		boolean isNotificationDisplayed = isElementDisplayed(By.xpath("//span[normalize-space()='Notificações']"));

		if (isNotificationDisplayed) {
			log("The Filler/s Notification Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Filler/s Notification Screen");
		return false;
	}
}