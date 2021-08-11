package com.everis.pages.packaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class FiveWhyManagementPage extends BasePage {

	public FiveWhyManagementPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "ShiftHistoryButtons_Events")
	protected WebElement eventsButton;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//td[1]/..//a[1]")
	protected WebElement possibleEventCategorize;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody//a[4]")
	protected WebElement eventFiveWhy;

	@FindBy(xpath = "//*[@id='tblEventsAll_stateEvents']/tbody/tr[1]/td[3]")
	protected WebElement possibleEventCategorizeSystem;

	@FindBy(xpath = "//a[normalize-space()='5 WHY']")
	protected WebElement fiveWhyButton;

	@FindBy(xpath = "//label[contains(text(), 'Descrição do problema')]//..//input")
	protected WebElement problemDescription;

	@FindBy(xpath = "//label[contains(text(), 'Ação corretiva')]//..//input")
	protected WebElement correctiveAction;

	@FindBy(xpath = "//input[@id='InputTaskItems_inputTask_46']")
	protected WebElement problemCause;

	@FindBy(xpath = "//a[normalize-space()='Salvar']")
	protected WebElement saveButton;

	@FindBy(xpath = "//span[normalize-space()='Categorize']")
	protected WebElement popUpTitle;

	@FindBy(xpath = "//span[normalize-space()='5 PORQUÊS']")
	protected WebElement fiveWhyTitle;

	@FindBy(xpath = "//label[normalize-space()='5 PORQUÊS']")
	protected WebElement fiveWhysBar;

	@FindBy(xpath = "//a[normalize-space()='PLANO DE AÇÃO']")
	protected WebElement actionPlan;

	@FindBy(id = "btnActions_add")
	protected WebElement addSubAction;

	@FindBy(xpath = "//label[contains(text(),'Dono do ação')]//following-sibling::input")
	protected WebElement actionOwner;

	@FindBy(id = "btnActions_sub")
	protected WebElement deleteSubAction;

	@FindBy(id = "5WhyManagement")
	protected WebElement fiveWhyManagementButton;

	@FindBy(xpath = "//select[@id='FilterEquipment_Filter']")
	protected WebElement filterEquipmentList;

	@FindBy(xpath = "//select[@id='FilterLine_Filter']")
	protected WebElement filterLineList;

	@FindBy(id = "btnRefresh_Button")
	protected WebElement refreshButton;

	@FindBy(xpath = "//*[@id='TableManagement']//tbody//a")
	protected WebElement editButton;

	@FindBy(id = "FilterDateRange_Start")
	protected WebElement startDateFilter;

	@FindBy(id = "FilterEquipment_Filter")
	protected WebElement equipmentDropdownList;

	@FindBy(id = "TableManagement")
	protected WebElement fiveWhyTable;

	@FindBy(xpath = "//input[@id='InputTaskItems_inputTask_67']")
	protected WebElement secondActionOwner;

	/**
	 * Create a new Five Why for a Event
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean createFiveWhy() throws Exception {
		// Filling the 5why
		fillFiveWhy();

		// Deleting the 5why
		deleteFiveWhy();

		clickElementJS(driver, saveButton);
		clickElementJS(driver, fiveWhyButton);
		// clickElementJS(driver, fiveWhyManagementButton);

		// Check if the Five Why was correctly created by checking the Five Why
		// Management for the equipment
		Select selectLine = new Select(filterLineList);
		selectLine.selectByVisibleText(dataMap.get("Line"));
		wait(2);

		Select selectEquipment = new Select(filterEquipmentList);
		selectEquipment.selectByVisibleText(dataMap.get("possibleEventCategorizeSystem"));

		startDateFilter.clear();
		startDateFilter.sendKeys(lastWeek());

		refreshButton.click();
		waitForPageToLoad(driver);

		boolean isActionOwnerDisplayed = isElementDisplayed(By.xpath("//span[contains(text(),'Action Owner')]"));
		if (isActionOwnerDisplayed) {
			log("Successfully created a Five Why");
		} else {
			logFail("The system was not able to correctly create a Five Why");
			return false;
		}
		return true;
	}

	/**
	 * Filling a Five Why and checking if the Five Why logic is correctly working
	 */
	public void fillFiveWhy() {
		// Go to events
		clickElementJS(driver, eventsButton);
		// Get the system name
		dataMap.put("possibleEventCategorizeSystem", possibleEventCategorizeSystem.getText());
		// Go to the event 5 why
		clickElementJS(driver, eventFiveWhy);
		waitElement(fiveWhyTitle, 5);

		scrollToElementJS(driver, fiveWhysBar);
		// Clear all the the 5whys
		driver.findElement(By.xpath("//*[@id='componentTab']/ul/li[1]/a")).click();
		problemDescription.clear();
		correctiveAction.clear();
		problemCause.clear();
		// Fill all the 5 whys
		problemDescription.sendKeys("Problem Description n1");
		correctiveAction.sendKeys("Corrective Action n1");
		problemCause.sendKeys("Problem Cause n1");

		for (int i = 2; i <= 5; i++) {
			scrollToElementJS(driver, fiveWhysBar);
			// Clear all the the 5whys
			driver.findElement(By.xpath("//*[@id='componentTab']/ul/li[" + i + "]/a")).click();
			problemDescription.clear();
			correctiveAction.clear();
			// Fill all the 5whys
			problemDescription.sendKeys("Problem Description n" + i);
			correctiveAction.sendKeys("Corrective Action n" + i);
			int problemCauseCounter = i - 1;
			// Checking if the 5 why is working
			driver.findElement(By.xpath(
					"//label[normalize-space()='Por que Problem Cause n" + problemCauseCounter + "?']//..//input"))
					.clear();
			driver.findElement(By.xpath(
					"//label[normalize-space()='Por que Problem Cause n" + problemCauseCounter + "?']//..//input"))
					.sendKeys("Problem Cause n" + i);
		}
		log(("Successfully filled all Five Why"));
	}

	/**
	 * Delete a Five Why Sub Action in the Action Plan Tab
	 * 
	 * @return
	 */
	public boolean deleteFiveWhy() {
		// Create a new Sub Action
		actionPlan.click();
		addSubAction.click();
		// Input a new Action Owner
		actionOwner.clear();
		actionOwner.sendKeys("Action Owner");
		log(("Successfully created a Action Owner"));

		// Adding a another Sub Action
		addSubAction.click();

		// Clean the Second Sub Action
		secondActionOwner.clear();
		secondActionOwner.sendKeys("Action Owner 2");
		// Deleting the Second Sub Action
		clickElementJS(driver, deleteSubAction);

		// Checking if the action was deleted
		if (!isElementDisplayed(By.xpath(
				"//*[contains(@name, 'Task.TaskItems.Indicator5Whys.actionOwner')][contains(@value, 'Action Owner 2')]"))) {
			log("Successfully deleted a Five Why");
		} else {
			logFail("The system was not able to delete a Five Why");
			return false;
		}
		return true;
	}

	/**
	 * Edit a Five Why by changing the Action Owner
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean editFiveWhy() throws Exception {
		Select selectLine = new Select(filterLineList);
		selectLine.selectByVisibleText(dataMap.get("Line"));
		wait(2);

		Select selectEquipment = new Select(filterEquipmentList);
		selectEquipment.selectByVisibleText(dataMap.get("possibleEventCategorizeSystem"));
		refreshButton.click();

		editButton.click();

		clickElementJS(driver, actionPlan);
		scrollToElementJS(driver, actionOwner);

		if (!isElementDisplayed(By.xpath("//label[contains(text(),'Dono do ação')]//following-sibling::input"))) {
			addSubAction.click();
		}
		actionOwner.clear();
		actionOwner.sendKeys("Edited Action Owner");
		clickElementJS(driver, saveButton);
		waitForPageToLoad(driver);
		wait(5);

		selectLine.selectByVisibleText(dataMap.get("Line"));
		selectEquipment.selectByVisibleText(dataMap.get("possibleEventCategorizeSystem"));
		refreshButton.click();
		wait(5);
		boolean isEditedActionOwnerDisplayed = isElementDisplayed(
				By.xpath("//span[normalize-space()='Edited Action Owner']"));
		if (isEditedActionOwnerDisplayed) {
			log("Successfully edited a Five Why");
		} else {
			logFail("The system was not able to edit a Five Why");
			return false;
		}
		return true;
	}

	/**
	 * Filter Five Whys by line to see if there's any no related Five Why
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean filterFiveWhy() throws Exception {
		clickElementJS(driver, fiveWhyButton);

		Select selectLine = new Select(filterLineList);
		selectLine.selectByVisibleText(dataMap.get("Line"));
		wait(2);

		List<WebElement> equipmentsList = new ArrayList<WebElement>();
		List<String> equipmentsNames = new ArrayList<String>();

		equipmentsList = driver.findElements(By.xpath("//select[@id='FilterEquipment_Filter']//option"));

		for (int i = 1; i <= equipmentsList.size(); i++) {
			startDateFilter.clear();
			startDateFilter.sendKeys(lastWeek());
			refreshButton.click();

			WebElement equipment = driver
					.findElement(By.xpath("//select[@id='FilterEquipment_Filter']//option[" + i + "]"));
			String name = equipment.getText();
			equipmentsNames.add(name);

			equipmentDropdownList.click();
			equipment.click();
			refreshButton.click();
			waitForPageToLoad(driver);

			boolean isTableDisplayed = isElementDisplayed(By.xpath("//*[@id='TableManagement']//tr"));

			if (!isTableDisplayed) {
				log("The selected equipment has no 5 Why for last week");
			} else {

				List<WebElement> equipmentFiveWhy = new ArrayList<WebElement>();
				equipmentFiveWhy = driver.findElements(By.xpath("//*[@id='TableManagement']//tr//td[1]//span"));

				for (int j = 1; j <= equipmentFiveWhy.size(); j++) {
					WebElement fiveWhyEquipment = driver
							.findElement(By.xpath("//*[@id='TableManagement']//tr[" + j + "]//td[1]//span"));
					String fiveWhyEquipmentName = fiveWhyEquipment.getText();

					if (!fiveWhyEquipmentName.equalsIgnoreCase(name)) {
						logFail("The 5 Whys Filter for the equipment: " + name
								+ " is not correctly working, the filter shown: " + fiveWhyEquipmentName);
					}
				}
			}
			log("The 5 Whys Filter for the equipment: " + name + " is correctly working");
		}
		return true;
	}

	// Get Last Week Date
	public String lastWeek() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		String newDate = format.format(cal.getTime());
		String startDate = newDate + " 12:00 AM";
		return startDate;
	}
}
