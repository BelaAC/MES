package com.everis.pages.packaging;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class DashboardPage extends BasePage {

	@FindBy(xpath = "//span[normalize-space()='L501 Dashboard']")
	protected WebElement title;

	@FindBy(id = "ShiftHistoryButtons_Events")
	protected WebElement eventsButton;

	@FindBy(xpath = "//span[normalize-space()='Name']/following-sibling::span")
	protected WebElement runningOrderNumberElement;

	@FindBy(xpath = "//*[@id='SystemJobListSelect']//li[1]//div[1]")
	protected WebElement firstOldOrder;

	public DashboardPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Verify if the Started Order is the same Order that was initialized
	 * 
	 * @throws Exception
	 */
	public boolean verifyStartedOrder() throws Exception {
		// waitElement(title, 5);
		waitElement(By.xpath("//span[contains(text(),'" + dataMap.get("Line") + "')]"), 10);
		boolean isStartedOrderDisplayed = isElementDisplayed(
				By.xpath("//span[normalize-space()='" + dataMap.get("StartedOrder") + "']"));

		if (isStartedOrderDisplayed) {
			log("The Order: " + dataMap.get("StartedOrder") + " was successfully started");
			return true;
		}
		logFail("The system was unable to start the Order Number: " + dataMap.get("StartedOrder"));
		return false;
	}

	/**
	 * Verify if the Ended Order is the same Order that was initialized
	 * 
	 * @throws Exception
	 */
	public boolean verifyEndedOrder() throws Exception {
		wait(10);
		boolean isEndedOrderDisplayed = isElementDisplayed(
				By.xpath("//span[normalize-space()='Name']/following-sibling::span[contains(text(), '"
						+ dataMap.get("RunningOrderNumber") + "')]"));

		if (isEndedOrderDisplayed) {
			logFail("The system was not able to ended the Order Number: " + dataMap.get("RunningOrderNumber"));
			return false;
		}
		log("The Order: " + dataMap.get("RunningOrderNumber") + " was successfully ended");
		return true;
	}

	/**
	 * Check if it is possible to go to the Old Orders Records in the Cockpit
	 * 
	 * @return
	 */
	public boolean oldOrderRecords() {
		// Saving the first Old Order Number
		String firstOldOrderNumber = firstOldOrder.getText();
		// Go to the first Old Order
		clickElementJS(driver, firstOldOrder);

		// Getting only the Numbers if the Order was Cancelled
		firstOldOrderNumber = StringUtils.substringBefore(firstOldOrderNumber, " ");

		waitElement(By.xpath("//span[normalize-space()='" + dataMap.get("Line") + " Dashboard - Ordem']"), 3);

		// Checking if the Old Order Record is available
		if (!isElementDisplayed(By.xpath("//li[contains(@class,'tsdetail-item-key-Name')]//span[contains(text(),'"
				+ firstOldOrderNumber + "')]"))) {
			log("The system was not able to acess the old order records");
			return false;
		}
		log("The system successfully acesse the old orders Records for the line: " + dataMap.get("Line"));
		return true;
	}

	// Click the Events Button
	public void clickEventsButton() throws Exception {
		clickElementJS(driver, eventsButton);
		log("Clicked the button: Events");
	}

	// Click the PTPs Button
	public void clickPTPButton(String ptpButton) throws Exception {
		// Get the PTP button
		WebElement chosenPtpButton = driver.findElement(
				By.xpath("//div[contains(text(), '" + ptpButton + "')]/../div[contains(text(),'Restante')]"));
		// Get the PTP remaining number
		String remainingNumber = chosenPtpButton.getText();
		// Get only the numbers
		dashboardValue = remainingNumber.replaceAll("[^0-9]", "");

		// Get the PTP button
		WebElement ptpOutOfSpec = driver.findElement(
				By.xpath("//div[contains(text(), '" + ptpButton + "')]/../div[contains(text(),'Out of Spec')]"));
		// Get the PTP out of Spec number
		String outOfSpecNumber = ptpOutOfSpec.getText();
		// Get only the numbers
		outOfSoecDashboardValue = outOfSpecNumber.replaceAll("[^0-9]", "");

		clickElementJS(driver, chosenPtpButton);
		log("Clicked the button: " + ptpButton);
	}

	// Gets the running order number to compare after
	public void caputureRunningOrderNumber() throws Exception {
		String runningOrderNumber = runningOrderNumberElement.getText();
		dataMap.put("RunningOrderNumber", runningOrderNumber);
	}
}