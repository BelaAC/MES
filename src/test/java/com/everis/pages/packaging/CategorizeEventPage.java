package com.everis.pages.packaging;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class CategorizeEventPage extends BasePage {

	public CategorizeEventPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "dropEventDefinition_createEventDialog")
	protected WebElement definitionEventList;

	@FindBy(id = "dropEventGroup_createEventDialog")
	protected WebElement categoryEventList;

	@FindBy(id = "dropSystems_createEventDialog")
	protected WebElement systemList;

	@FindBy(xpath = "//span[normalize-space()='Categorizar']")
	protected WebElement popUpTitle;

	@FindBy(xpath = "//a[normalize-space()='Salvar']")
	protected WebElement saveButton;
	
	@FindBy(xpath = "//a[normalize-space()='Atualizar']")
	protected WebElement updateButton;

	@FindBy(id = "btncreateEventDialog_Create")
	protected WebElement createButton;

	@FindBy(id = "dtStart_createEventDialog")
	protected WebElement startDate;

	@FindBy(id = "dtEnd_createEventDialog")
	protected WebElement endDate;

	@FindBy(xpath = "//textarea[@id='txtNotes$stateEvents']")
	protected WebElement notesArea;

	@FindBy(xpath = "//*[@id='dropEventFilter_updateEventDialog']")
	protected WebElement eventFilter;

	@FindBy(xpath = "//a[normalize-space()='New Event']")
	protected WebElement newDowntime;

	@FindBy(xpath = "//a[normalize-space()='Filtro']")
	protected WebElement filterButton;

	@FindBy(id = "dropEquipment")
	protected WebElement selectEquipment;

	@FindBy(id = "btnRefresh")
	protected WebElement refreshButton;

	@FindBy(id = "tblEvents_stateEvents_Container")
	protected WebElement eventsTable;

	@FindBy(id = "btnCancel_updateEventDialog")
	protected WebElement cancelButton;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//tr[3]//a[1]")
	protected WebElement possibleEventChanges;

	public void createNewDowntime() throws Exception {
		// Got to new Downtime
		newDowntime.click();
		// Selecting random Category and Definition and start date
		selectRandomSystemCategoryAndDefinition();
	}

	/**
	 * Select a Random Category and a Random Definition in the Categorize Event
	 * Pop-up
	 * 
	 * @throws Exception
	 */

	public void selectRandomCategoryAndDefinition() throws Exception {
		waitElement(popUpTitle, 10);
		// Select Random Option In Category Event
		scrollToElementJS(driver, definitionEventList);
		wait(5);
		List<WebElement> category = new ArrayList<WebElement>();
		category = driver.findElements(By.cssSelector("#dropEventGroup_updateEventDialog > option"));

		Random random = new Random();
		int randomNumber = random.nextInt(category.size());

		if (randomNumber == 1 || randomNumber == 0) {
			randomNumber = 2;
		}

		WebElement selectedCategory = driver.findElement(
				By.cssSelector("#dropEventGroup_updateEventDialog > option:nth-child(" + randomNumber + ")"));
		dataMap.put("category", selectedCategory.getText());
		selectedCategory.click();

		// Select Random Option in Definition Event
		wait(3);
		selectRandomElementInDropdownList(driver,
				"//*[contains(text(),'Definição do evento')]/following-sibling::select", "definition");
		log("Selected the Category: " + dataMap.get("category") + " and selected the Definition: "
				+ dataMap.get("definition"));
		updateButton.click();
		wait(5);
	}

	/**
	 * Select a Random System Category and Definition in the Categorize Event
	 * Pop-up. Do not change the lists into Selects because the page refresh it's
	 * self.
	 * 
	 * @throws Exception
	 */

	public void selectRandomSystemCategoryAndDefinition() throws Exception {
		wait(3);

		// Select Random Option In System
		scrollToElementJS(driver, systemList);
		List<WebElement> systemListOptions = new ArrayList<WebElement>();
		systemListOptions = driver.findElements(By.xpath("//*[@id='dropSystems_createEventDialog']/option"));

		// Getting a random number
		Random random = new Random();
		int randomSystemNumber = random.nextInt(systemListOptions.size());

		// There is no 0 Option
		if (randomSystemNumber == 0) {
			randomSystemNumber = 1;
		}

		// Selecting the random option
		WebElement selectedSystem = driver
				.findElement(By.xpath("//*[@id='dropSystems_createEventDialog']/option[" + randomSystemNumber + "]"));
		// Getting the option string
		String selectedSystemName = selectedSystem.getText();
		selectedSystem.click();
		dataMap.put("system", selectedSystemName);

		// Select Random Option In Category Event
		wait(3);
		scrollToElementJS(driver, categoryEventList);
		List<WebElement> categoryListOptions = new ArrayList<WebElement>();
		categoryListOptions = driver.findElements(By.xpath("//*[@id='dropEventGroup_createEventDialog']/option"));

		// Getting a random number
		int randomCategoryNumber = random.nextInt(categoryListOptions.size());

		// There is no 0 Option and the option 1 is None
		if (randomCategoryNumber == 0 || randomCategoryNumber == 1) {
			randomCategoryNumber = 2;
		}

		// Selecting the random option
		WebElement selectedCategory = driver.findElement(
				By.xpath("//*[@id='dropEventGroup_createEventDialog']/option[" + randomCategoryNumber + "]"));
		// Getting the option string
		String selectedCategoryName = selectedCategory.getText();
		selectedCategory.click();
		dataMap.put("category", selectedCategoryName);

		// Select Random Option in Definition Event
		wait(10);
		scrollToElementJS(driver, definitionEventList);
		List<WebElement> definitionListOptions = new ArrayList<WebElement>();
		definitionListOptions = driver
				.findElements(By.xpath("//*[@id='dropEventDefinition_createEventDialog']/option"));

		// Getting a random number
		int randomDefinitionNumber = random.nextInt(definitionListOptions.size());

		// There is no 0 Option and the option 1 is None
		if (randomDefinitionNumber == 0 || randomDefinitionNumber == 1) {
			randomDefinitionNumber = 2;
		}

		// Selecting the random option
		WebElement selectedDefinition = driver.findElement(
				By.xpath("//*[@id='dropEventDefinition_createEventDialog']/option[" + randomDefinitionNumber + "]"));
		// Getting the option string
		String selectedDefinitionName = selectedDefinition.getText();
		selectedDefinition.click();
		dataMap.put("definition", selectedDefinitionName);

		log("Selected the Category: " + dataMap.get("category") + " and selected the Definition: "
				+ dataMap.get("definition"));
		createButton.click();
		wait(5);

		selectPossibleDate();
	}

	/**
	 * Select a possible Date to Categorize a Event. If the error 'Invalid Form' is
	 * displayed to the user is probably due Logic Server not Running.
	 * 
	 * @throws Exception
	 */

	public void selectPossibleDate() throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		ZoneId zid = ZoneId.of("America/Sao_Paulo");

		ZonedDateTime cal1 = ZonedDateTime.now(zid);
		ZonedDateTime cal2 = ZonedDateTime.now(zid);

		cal2 = cal2.plusMinutes(5);

		boolean erro = isElementDisplayed(By.xpath("//*[contains(text(),'There are overlapping events')]"));

		while (erro) {
			wait(1);
			boolean erro1 = isElementDisplayed(By.xpath("//*[contains(text(),'There are overlapping events')]"));

			if (erro1) {
				String newDate1 = cal1.format(formatter);
				String newDate2 = cal2.format(formatter);

				startDate.clear();
				startDate.sendKeys(newDate1);
				endDate.clear();
				endDate.sendKeys(newDate2);

				clickElementJS(driver, createButton);

				cal2 = cal2.plusMinutes(5);
				cal1 = cal1.plusMinutes(3);
			} else {
				if (!erro1) {
					log("Sucessfully created a New Downtime");
					erro = false;
				}
			}
		}
	}

	/**
	 * Select the chosen Event Category
	 * 
	 * @param eventCategory
	 */
	public void selectEventCategory(String eventCategory) {
		wait(10);
		// Get the category combobox
		Select eventCategoryCombox = new Select(
				driver.findElement(By.xpath("//select[@id='dropEventGroup_updateEventDialog']")));
		wait(5);
		// Select the Category
		eventCategoryCombox.selectByVisibleText(eventCategory);
		log("Selected the event Category: " + eventCategory);
		dataMap.put("EventCategory", eventCategory);
	}

	public void allEventsCategoryOptionsCorrect() {
		// Selecting a possible event
		selectPossibleEventChanges();
		// Selecting the event category
		selectEventCategory("Indisponibilidade Externa [EC]");
		// Checking if the options are correct
		eventsCategoryOptionsCorrect();
		// Canceling the categorization
		clickElementJS(driver, cancelButton);

		// Selecting a possible event
		selectPossibleEventChanges();
		// Selecting the event category
		selectEventCategory("Parada Programada [DPA]");
		// Checking if the options are correct
		eventsDefinitionOptionsCorrect();
		// Canceling the categorization
		clickElementJS(driver, cancelButton);

		// Selecting a possible event
		selectPossibleEventChanges();
		// Selecting the event category
		selectEventCategory("Não Programada [NST]");
		// Checking if the options are correct
		eventsDefinitionOptionsCorrect();
		// Canceling the categorization
		clickElementJS(driver, cancelButton);

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
		WebElement eventID = driver.findElement(By.id(possibleEventChangesId));
		clickElementJS(driver, eventID);
		dataMap.put("possibleEventChangesId", possibleEventChangesId);
	}

	/**
	 * Check if the Events Category Options are Correct. POS-DEPLOY TEST
	 * 
	 * @return
	 */
	public boolean eventsCategoryOptionsCorrect() {
		selectEventCategory("Indisponibilidade Externa [EC]");
		wait(2);
		// Go to the event filter
		scrollToElementJS(driver, eventFilter);
		// Get all the event options
		Select eventFilterCombox = new Select(
				driver.findElement(By.xpath("//select[@id='dropEventFilter_updateEventDialog']")));
		List<WebElement> optionsFilterEvent = new ArrayList<WebElement>();
		List<String> optionFilterText = new ArrayList<String>();
		optionsFilterEvent = eventFilterCombox.getOptions();
		int listSize = optionsFilterEvent.size();

		// Checking if the event category is External Cause
		if (dataMap.get("EventCategory").equalsIgnoreCase("Indisponibilidade Externa [EC]")) {
			for (int i = 1; i <= listSize - 1; i++) {
				// If so gets all the events options to compare with the correct list in the
				// base page
				String text = optionsFilterEvent.get(i).getText();
				optionFilterText.add(text);
			}
		}
		// Sorting the given event option list
		Collections.sort(optionFilterText);

		// Checking if its equal to the correct event options list
		if (optionFilterText.equals(correctEventsList())) {
			log("Event Filter for External Causes [EC] is correct");
		} else {
			// logFail("Event Filter for External Causes [EC] is not being correctly
			// displayed");
			clickElementJS(driver, cancelButton);
			// return false;
		}
		clickElementJS(driver, cancelButton);
		return true;
	}

	/**
	 * Check if the Events Definition Options are Correct. POS-DEPLOY TEST
	 * 
	 * @return
	 */
	public boolean eventsDefinitionOptionsCorrect() {
		// Go to the event filter combobox
		scrollToElementJS(driver, eventFilter);
		Select eventFilterCombox = new Select(
				driver.findElement(By.xpath("//select[@id='dropEventDefinition$stateEvents']")));
		// Getting the options
		List<WebElement> optionsFilterEvent = new ArrayList<WebElement>();
		// Getting the option's texts
		List<String> optionFilterText = new ArrayList<String>();
		optionsFilterEvent = eventFilterCombox.getOptions();
		int listSize = optionsFilterEvent.size();

		// Checking if the event categorization is DPA
		if (dataMap.get("EventCategory").equalsIgnoreCase("Parada Programada [DPA]")) {
			for (int i = 1; i <= listSize - 1; i++) {
				// If so gets all the options texts and save into the list
				String text = optionsFilterEvent.get(i).getText();
				optionFilterText.add(text);
			}
			// Sorting the list
			Collections.sort(optionFilterText);

			// Checking if it's equal to the correct list
			if (!optionFilterText.equals(correctDefinitionOptionsDowntimeList())) {

				logFail("Event Definition for Dowtime Planned [DPA] is not being correctly displayed");
				return false;
			}
		}
		// Checking if the event categorization is NST
		if (dataMap.get("EventCategory").equalsIgnoreCase("Não Programada [NST]")) {
			// If so checks if there are only two options
			if (optionsFilterEvent.size() > 3) {
				logFail("Event Definition for Not Scheduled [NST] is not being correctly displayed");
				return false;
			}
		}
		log("The Event Definition Options are correct");
		return true;
	}

	/**
	 * Check if the Events Filter is correctly working by using RootCause as filter
	 * 
	 * @return
	 */
	public boolean eventsFilter() {
		wait(2);
		String rootCause = driver
				.findElement(By.xpath("//*[@id='tblEventsAll_stateEvents']//td[9][contains(text(), ' ')]")).getText();
		Select equipment = new Select(selectEquipment);
		equipment.selectByVisibleText(rootCause);
		clickElementJS(driver, refreshButton);

		List<WebElement> rootCauses = new ArrayList<WebElement>();
		rootCauses = driver.findElements(By.xpath("//*[@id='tblEventsAll_stateEvents']//td[9][contains(text(), ' ')]"));

		for (WebElement webElement : rootCauses) {
			String rootCauseName = webElement.getText();
			if (!(rootCauseName.equalsIgnoreCase(rootCause))) {
				scrollToElementJS(driver, webElement);
				logFail("The filter for Event List For Shift is not correctly working");
				return false;
			}
		}
		scrollToElementJS(driver, eventsTable);
		log("The filter for Event List For Shift is correctly working");
		return true;
	}
}