package com.everis.pages.packaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class EventListShiftHistoryPage extends BasePage {

	public EventListShiftHistoryPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Par')]/..//a[2]")
	protected WebElement possibleEventSplit;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Des')]/..//a[2]")
	protected WebElement possibleEventSplitDisconnected;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Par')]/..//a[3]")
	protected WebElement possibleEventDelete;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Par')]/../td[6]")
	protected WebElement possibleEventDuration;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Des')]/../td[6]")
	protected WebElement possibleEventDurationDisconnected;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Parada')]/../td[2]/input")
	protected WebElement selectStopEvent;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Falta')]/../td[2]/input")
	protected WebElement selectStarvedEvent;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Des')]/..//input")
	protected WebElement selectDisconnectedEvent;

	@FindBy(id = "btnEventActions_stateEvents_Delete")
	protected WebElement deleteEventsButton;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[1]/..//a[1]")
	protected WebElement possibleEventCategorize;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//tr[3]//a[1]")
	protected WebElement possibleEventChanges;

	@FindBy(id = "tblEvents_stateEvents_Container")
	protected WebElement eventsTable;

	@FindBy(id = "btnEventActions_stateEvents_Acknowledge")
	protected WebElement multiCategorizeEvents;

	@FindBy(xpath = "//a[normalize-space()='Filtro']")
	protected WebElement filterButton;

	@FindBy(id = "dtShift")
	protected WebElement startDate;

	@FindBy(id = "btnRefresh")
	protected WebElement refresh;

	@FindBy(id = "Buttons_Split_Split")
	protected WebElement splitButton;

	@FindBy(xpath = "//span[normalize-space()='Categorizar']")
	protected WebElement popUpTitle;

	@FindBy(id = "dropEventGroup_updateEventDialog")
	protected WebElement definitionEventList;

	@FindBy(xpath = "//a[normalize-space()='Salvar']")
	protected WebElement saveButton;

	/**
	 * Verify if the Event List for Shift History Screen is being displayed
	 * 
	 * @throws Exception
	 */
	public boolean verifyEventListShiftDisplayed() throws Exception {
		boolean isEventListShiftDisplayed = isElementDisplayed(By.id("tblEvents_stateEvents_Container"));

		if (isEventListShiftDisplayed) {
			log("The Event List for Shift History Screen was successfully loaded");
			return true;
		}
		logFail("The system was unable to load the Event List for Shift History Screen");
		return false;
	}

	/**
	 * Verify if the selected Event List matches the table
	 * 
	 * @param eventsTable - The Event List Table
	 * @return
	 * @throws Exception
	 */
	public boolean verifyEventsTable(String eventsTable) throws Exception {
		boolean isCategorizedEventsTableDisplayed = isElementDisplayed(
				By.id("tblEvents" + eventsTable + "_stateEvents"));

		if (isCategorizedEventsTableDisplayed) {
			log("The Categorized Events Table was successfully displayed");
			return true;
		}
		logFail("The system was unable to load the Categorized Events Table");
		return false;
	}

	// Click the chosen Event Option
	public void clickEventsOption(String eventsOption) {
		driver.findElement(By.xpath("//label[normalize-space()='" + eventsOption + "']")).click();
		log("Selected the Events' Radio Button: " + eventsOption);
	}

	/**
	 * Select a possible event to split and get its duration to compare after the
	 * split
	 * 
	 * @throws ParseException
	 */
	public void selectPossibleEventSplit() throws ParseException {
		// Selecting the start date as last month
		selectFilterLastMonth();

		// Checking if the line L521 or L536
		if (dataMap.get("Line").equalsIgnoreCase("L521") || dataMap.get("Line").equalsIgnoreCase("L536")) {
			// If so there are mainly disconnected evens
			String eventDuration = possibleEventDurationDisconnected.getText();
			clickElementJS(driver, possibleEventSplitDisconnected);
			dataMap.put("EventDuration", eventDuration);

		} else {
			// If not there are mainly Stopped event
			String eventDuration = possibleEventDuration.getText();
			clickElementJS(driver, possibleEventSplit);
			dataMap.put("EventDuration", eventDuration);
		}

		// Selecting split
		splitButton.click();

		// Checking if the event was successfully splitted
		verifySplittedEvent();
	}

	/**
	 * Select Possible Event to Delete
	 */
	public void selectPossibleEventDelete() {
		clickElementJS(driver, possibleEventDelete);
		wait(5);
	}

	// Filter Events from Last Month until now
	public void selectFilterLastMonth() {
		wait(5);
		startDate.clear();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String newDate = format.format(cal.getTime());
		startDate.sendKeys(newDate);
		refresh.click();
	}

	/**
	 * Categorize any Event
	 * 
	 * @throws Exception
	 */
	public void categorizeEvent() throws Exception {
		// Getting the event Id
		String possibleEventCategorizeId = possibleEventCategorize.getAttribute("id");
		dataMap.put("possibleEventCategorizeId", possibleEventCategorizeId);
		clickElementJS(driver, possibleEventCategorize);

		waitElement(popUpTitle, 5);
		// Select Random Option In Category Event
		scrollToElementJS(driver, definitionEventList);
		selectRandomElementInDropdownList(driver, "//select[@id='dropEventGroup_updateEventDialog']", "category");
		// Select Random Option in Definition Event
		wait(3);
		selectRandomElementInDropdownList(driver, "//select[@id='dropEventDefinition_updateEventDialog']",
				"definition");
		log("Selected the Category: " + dataMap.get("category") + "and selected the Definition: "
				+ dataMap.get("definition"));
		// Saving
		saveButton.click();
		wait(5);

		// Checking the event specifications
		verifySpecificationForEvent();
	}

	/**
	 * Check if the splitted Event duration is correct
	 * 
	 * @return
	 * @throws ParseException
	 */

	public boolean verifySplittedEvent() throws ParseException {
		// Checking if the line L521 or L536
		if (dataMap.get("Line").equalsIgnoreCase("L521") || dataMap.get("Line").equalsIgnoreCase("L536")) {
			// If so there are mainly disconnected evens
			String input = possibleEventDurationDisconnected.getText();

			// Comparing the difference between the before and the after split event
			// duration
			LocalTime afterDuration = LocalTime.parse(input);
			LocalTime beforeDuration = LocalTime.parse(dataMap.get("EventDuration"));
			int value = afterDuration.compareTo(beforeDuration);

			// Checking the difference
			if (value > 0) {
				logFail("The Event Duration Time before the split is greater than the Event Duration time after the split");
				return false;
			} else if (value == 0) {
				logFail("The Event Duration Time before and after the split are equal");
				return false;
			}
			log("The Event was successfully splitted");
		} else {
			// If so there are mainly stopped evens
			String input = possibleEventDuration.getText();

			// Comparing the difference between the before and the after split event
			// duration
			LocalTime afterDuration = LocalTime.parse(input);
			LocalTime beforeDuration = LocalTime.parse(dataMap.get("EventDuration"));
			int value = afterDuration.compareTo(beforeDuration);

			// Checking the difference
			if (value > 0) {
				logFail("The Event Duration Time before the split is greater than the Event Duration time after the split");
				return false;
			} else if (value == 0) {
				logFail("The Event Duration Time before and after the split are equal");
				return false;
			}
			log("The Event was successfully splitted");
		}
		return true;
	}

	/**
	 * Select two different options. For the Lines L536 and L521 get the
	 * disconnected events, because they are more common in those lines
	 */
	public void selectTwoPossibleEvent() {
		// Select the filter date as last month
		selectFilterLastMonth();
		// Checking if the line is L521 or L536
		if (dataMap.get("Line").equalsIgnoreCase("L536") || dataMap.get("Line").equalsIgnoreCase("L521")) {
			// Because there are mainly disconnected events
			// Getting the disconnected events
			List<WebElement> disconnected = new ArrayList<WebElement>();
			disconnected = driver.findElements(
					By.xpath("//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Desco')]/../td[2]/input"));
			for (int i = 0; i <= 1; i++) {
				// Getting two disconnected events
				clickElementJS(driver, disconnected.get(i));
			}
			// Deleting
			clickElementJS(driver, deleteEventsButton);
		} else {
			// Getting a Stop event
			clickElementJS(driver, selectStopEvent);
			// Getting a Starved event
			clickElementJS(driver, selectDisconnectedEvent);
			// Deleting
			clickElementJS(driver, deleteEventsButton);
		}
	}

	/**
	 * Select only one event to categorize
	 */
	public void selectPossibleEventCategorize() {
		scrollToElementJS(driver, possibleEventCategorize);
		String possibleEventCategorizeId = possibleEventCategorize.getAttribute("id");
		clickElementJS(driver, possibleEventCategorize);
		dataMap.put("possibleEventCategorizeId", possibleEventCategorizeId);
	}

	/**
	 * Select two different events to Multi Categorize. For the Lines L536 and L521
	 * get the disconnected events, because they are more common in those lines
	 */

	public void selectTwoPossibleEventsCategorize() {
		// Select the filter date as last month
		selectFilterLastMonth();

		// Checking if the line is L521 or L536
		if (dataMap.get("Line").equalsIgnoreCase("L536") || dataMap.get("Line").equalsIgnoreCase("L521")) {
			// Because there are mainly disconnected events
			// Getting the disconnected events
			List<WebElement> disconnected = new ArrayList<WebElement>();
			disconnected = driver.findElements(
					By.xpath("//*[@id='tblEventsAll_stateEvents']/tbody//td[contains(text(),'Desc')]/../td[2]/input"));
			if (disconnected.size() > 0) {
				for (int i = 0; i <= 1; i++) {
					String possibleEventCategorizeId = disconnected.get(i).getAttribute("id");
					clickElementJS(driver, disconnected.get(i));
					switch (i) {
					case 0:
						dataMap.put("firstPossibleEventCategorizeId", possibleEventCategorizeId);
						break;

					case 1:
						dataMap.put("secondPossibleEventCategorizeId", possibleEventCategorizeId);
						break;

					default:
						break;
					}
				}

				clickElementJS(driver, multiCategorizeEvents);
			} else {
				log("The Line doesn't have any disconnected event");
			}
		} else {
			// Select first Event
			clickElementJS(driver, selectDisconnectedEvent);
			String firstPossibleEventCategorizeId = selectDisconnectedEvent.getAttribute("id");
			dataMap.put("firstPossibleEventCategorizeId", firstPossibleEventCategorizeId);
			
			// Select second Event
			clickElementJS(driver, selectStopEvent);
			String secondPossibleEventCategorizeId =
					selectStopEvent.getAttribute("id");
			dataMap.put("secondPossibleEventCategorizeId",
			secondPossibleEventCategorizeId);
			clickElementJS(driver, multiCategorizeEvents);
		}

	}

	/**
	 * Check if the edited event definitions are correct
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifySpecificationForEvent() throws Exception {
		waitForPageToLoad(driver);
		if (!isElementDisplayed(By.xpath("//*[@id='" + dataMap.get("possibleEventCategorizeId")
				+ "']//ancestor::tr//td[contains(text(),'" + dataMap.get("definition") + "')]"))) {
			log("The event's specifications are incorrect");
			return false;
		}
		log("The event's specifications are correct");
		return true;
	}

	/**
	 * Check if the edited event specifications are correct
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifySpecificationForEvents() throws Exception {
		waitForPageToLoad(driver);
		boolean isFirstSpecificationDisplayed = isElementDisplayed(
				By.xpath("//*[@id='" + dataMap.get("firstPossibleEventCategorizeId")
						+ "']//ancestor::tr//td[contains(text(),'" + dataMap.get("definition") + "')]"));
		boolean isSecondSpecificationDisplayed = isElementDisplayed(
				By.xpath("//*[@id='" + dataMap.get("secondPossibleEventCategorizeId")
						+ "']//ancestor::tr//td[contains(text(),'" + dataMap.get("definition") + "')]"));

		if (!isFirstSpecificationDisplayed && isSecondSpecificationDisplayed) {
			log("The event's specifications are incorrect");
			return false;
		}
		log("The event's specifications are correct");
		return true;
	}

	/**
	 * Select one possible event to edit
	 */
	public void selectPossibleEventChanges() {
		// Go to the first possible event
		scrollToElementJS(driver, possibleEventChanges);
		// Get the event id
		String possibleEventChangesId = possibleEventChanges.getAttribute("id");
		// Select the event
		clickElementJS(driver, possibleEventChanges);
		dataMap.put("possibleEventChangesId", possibleEventChangesId);
	}

	/**
	 * Check if the last character (&)from the Notes Limit was excluded
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean verifyNotesChanges() throws Exception {
		waitForPageToLoad(driver);
		boolean isLastLetterDisplayed = isElementDisplayed(
				By.xpath("//a[@id='" + dataMap.get("possibleEventChangesId") + "']/../../td[contains(text(),'&')]"));
		if (!isLastLetterDisplayed) {
			log("The system successfully excluded the surplus character");
			return true;
		}
		logFail("The system was not able to excluded the surplus character");
		return false;
	}

	/**
	 * Check if all the radio Buttons for the Event List Shift are correctly working
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean varifyAllRadioButtons() throws Exception {
		// Get all the radio buttons
		List<WebElement> radioButtons = new ArrayList<WebElement>();
		radioButtons = driver.findElements(By.xpath("//*[@id='chkListOptions_Wrapper']/div/div"));

		for (WebElement webElement : radioButtons) {
			String radioButtonOption = webElement.getText();

			switch (radioButtonOption) {
			case "Todos":
				dataMap.put("radioButtonOption", "All");
				break;

			case "Sem categoria":
				dataMap.put("radioButtonOption", "Uncategorized");
				break;

			case "Categorizado":
				dataMap.put("radioButtonOption", "Categorized");
				break;

			default:
				break;
			}

			// Select the button
			webElement.click();
			waitForPageToLoad(driver);
			// Check if the tables are correctly working
			driver.findElement(
					By.xpath("//table[@id='tblEvents" + dataMap.get("radioButtonOption") + "_stateEvents']"));
			log("The Radio Button: " + dataMap.get("radioButtonOption") + " is working");
		}
		return true;
	}
}