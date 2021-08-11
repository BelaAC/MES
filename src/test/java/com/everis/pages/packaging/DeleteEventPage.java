package com.everis.pages.packaging;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class DeleteEventPage extends BasePage {

	public DeleteEventPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "btnDelete_deleteEventDialog")
	protected WebElement deleteButton;

	@FindBy(xpath = "//*[normalize-space()='DeleteEvent(s)']")
	protected WebElement popUpTitle;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Para')]/..//a[3]")
	protected WebElement possibleEventDelete;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Dis')]/..//a[3]")
	protected WebElement possibleEventDeleteDisconnected;

	/**
	 * Multi Delete events
	 */
	public void deleteEvents() {
		waitElement(popUpTitle, 3);
		deleteButton.click();
		log("Successfully Multi Deleted Events");
	}

	/**
	 * Delete only one event
	 */
	public void deleteEvent() {
		// Checking if the line L521 or L536
		if (dataMap.get("Line").equalsIgnoreCase("L521") || dataMap.get("Line").equalsIgnoreCase("L536")) {
			// If so there are mainly disconnected evens
			clickElementJS(driver, possibleEventDeleteDisconnected);
			waitElement(popUpTitle, 3);
			// Delete the Event
			deleteButton.click();
		} else {
			// If not there are mainly Stopped event
			clickElementJS(driver, possibleEventDelete);
			waitElement(popUpTitle, 3);
			// Delete the Event
			deleteButton.click();
		}
		log("Successfully Deleted the Event");
	}
}