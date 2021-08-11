package com.everis.pages.packaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class CreatePackagingOrderPage extends BasePage {

	@FindBy(id = "InputPlannedProductionCount")
	protected WebElement plannedProduction;

	@FindBy(xpath = "//input[@id='InputName']")
	protected WebElement orderNumber;

	@FindBy(id = "ddlIsProductionStart")
	protected WebElement productionStart;

	@FindBy(id = "FormButtons_Save")
	protected WebElement saveButton;

	@FindBy(xpath = "//a[normalize-space()='Abrir Lote']")
	protected WebElement scheduleButton;

	@FindBy(id = "ButtonNewOrder_New")
	protected WebElement newOrder;

	@FindBy(xpath = "//a[normalize-space()='Editar']")
	protected WebElement editOrder;

	@FindBy(xpath = "//tbody//td[7]/a[1]")
	protected WebElement plannedPieces;

	@FindBy(id = "BtnForm_Save")
	protected WebElement startButton;

	@FindBy(xpath = "//a[normalize-space()='Início']")
	protected WebElement startOrderButton;

	@FindBy(xpath = "//*[@id='DetailsJob']//li[1]/span[2]")
	protected WebElement startingOrderNumber;

	@FindBy(id = "ButtonMenu_Job_End")
	protected WebElement endOrderButton;

	@FindBy(id = "InputProductPicker")
	protected WebElement productList;

	@FindBy(xpath = "//li[@class='tsdetail-item-key-Name']//span[@class='tsdetail-value']")
	protected WebElement runningOrderNumber;

	@FindBy(xpath = "//span[contains(text(), 'Dashboard')]")
	protected WebElement dashboardTitle;

	@FindBy(xpath = "//a[normalize-space()='Excluir Ordem']")
	protected WebElement deleteButton;

	@FindBy(id = "TablePlannedOrders")
	protected WebElement plannedrdersTable;

	@FindBy(id = "ButtonMenu_Job_Cancel")
	protected WebElement cancelOrderButton;

	@FindBy(id = "ButtonMenu_Job_UndoCancel")
	protected WebElement undoCancelOrderButton;

	@FindBy(id = "btnCancel_UndoCancelOrder")
	protected WebElement confirmatioUndoCancelOrderButton;

	@FindBy(id = "btnCancel_CancelOrder")
	protected WebElement confirmCancelOrderButton;

	@FindBy(id = "BtnEnd_End")
	protected WebElement confirmEndOrderButton;

	public CreatePackagingOrderPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Opening a order in packaging to transfer a batch form brewing
	 * 
	 * @throws Exception
	 */
	public void openOrderForTransferFromBrewing() throws Exception {
		driver.findElement(By
				.xpath("//div[normalize-space()='" + choseRandomLine() + "']/../..//button[contains(text(),'Linha')]"))
				.click();
		createSpecificNewOrder();
		startSpecificOrder();

		String line = dashboardTitle.getText();
		line = StringUtils.substringBefore(line, " ");
		dataMap.put("Line", line);
	}

	public String choseRandomLine() {
		// Create a list of String type
		List<String> list = new ArrayList<>();
		// Add only the lines that accept SKOL in ArrayList
		list.add("L501");
		list.add("L505");
		list.add("L506");

		// take a random element from list
		return getRandomElement(list);
	}

	/**
	 * Function select an element base on index and return an element
	 * 
	 * @param list - A string List
	 * @return
	 */
	public String getRandomElement(List<String> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	/**
	 * Create a new Order from the minimum number of each product. If a order is
	 * created and started with a planned Pieces Number less than it should be
	 * system crash
	 * 
	 * @throws Exception
	 */
	public void createNewOrder() throws Exception {
		// Go to open a new Order
		scheduleButton.click();
		newOrder.click();

		// Selecting a random product to the order
		randomProductType();

		// Checking if the Planned Production is enable
		if (plannedProduction.isEnabled()) {
			// If so clears the planned production
			plannedProduction.clear();
			// Insert a random planned production greater than 100
			plannedProduction.sendKeys(randomNumberGreaterThan100());
			dataMap.put("plannedPiecesNumber", randomNumberGreaterThan100());
		} else {
			// If not just save the planned production as 0
			dataMap.put("plannedProduction", "0");
		}

		// Getting the Order number
		capturingOrderNumber();
		// Saving the Order
		clickingSaveButton();
		wait(10);
		// Checking if the Order Number and Specifications are correct
		checkingOrderNumberAndSpecifications();
	}

	/**
	 * Create a new Order for the specific product
	 * 
	 * @throws Exception
	 */
	public void createSpecificNewOrder() throws Exception {
		scheduleButton.click();
		newOrder.click();
		productList.click();
		driver.findElement(By.xpath("//*[@id='InputProductPicker']/option[contains(text(),'SKOL')]")).click();

		if (plannedProduction.isEnabled()) {
			plannedProduction.clear();
			plannedProduction.sendKeys(randomNumberGreaterThan100());
			dataMap.put("plannedPiecesNumber", randomNumberGreaterThan100());
		} else {
			dataMap.put("plannedProduction", "0");
		}

		capturingOrderNumber();
		clickingSaveButton();
		wait(10);
		checkingOrderNumberAndSpecifications();
	}

	public void editOrder() throws Exception {
		// Getting the planned piece number
		String plannedPiecesNumber = plannedPieces.getText();
		dataMap.put("PlannedPiecesNumber", plannedPiecesNumber);
		// Editing the first order
		clickElementJS(driver, editOrder);
		log("Selected edit the first order");

		// Checking if the Production Start is enable to edit
		if (isElementDisplayed(By.id("ddlIsProductionStart"))) {
			// If so select a random Production Start
			randomProductionStart();
		}

		// Checking if the Planned Production Count is enable
		if (plannedProduction.isEnabled()) {
			// If so clears the Planned Production Count
			plannedProduction.clear();
			// Insert a number greater than 100
			plannedProduction.sendKeys(randomNumberGreaterThan100());
		} else {
			// If not just save the Planned Production Count as 0
			dataMap.put("plannedProduction", "0");
		}

		// Getting the Edited Order Number
		capturingOrderNumberEdit();
		// Saving the Edited order
		clickingSaveButton();
		// Checking if the order's specification are correct and updated
		checkingEditOrder();
	}

	/**
	 * Start a new Order. It is only possible to start a order if there's no running
	 * order
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean startOrder() throws Exception {
		// Start the previous created Order
		WebElement startOrder = driver.findElement(By.xpath("//*[@id='TablePlannedOrders']//tr//a[contains(text(),'"
				+ dataMap.get("Delivered Order Number") + "')]/ancestor::tr//a[contains(text(),'Início')]"));
		clickElementJS(driver, startOrder);

		// Confirming the Start
		clickElementJS(driver, startButton);
		waitForPageToLoad(driver);

		// Checking if the Order was started
		waitForElement(dashboardTitle, 5);
		if (!isElementDisplayed(By.xpath(
				"//*[@id='DetailJob']//span[contains(text(), '" + dataMap.get("Delivered Order Number") + "')]"))) {
			logFail("The system was not able to start the Order");
			return false;
		}
		log("The Order Number: " + dataMap.get("Delivered Order Number") + " was successfully started");
		return true;
	}

	/**
	 * This method start the created order in the createdSpecifOrder method
	 * 
	 * @throws Exception
	 */
	public void startSpecificOrder() throws Exception {
		WebElement stratedOrder = driver
				.findElement(By.xpath("//a[normalize-space()='" + dataMap.get("Delivered Order Number")
						+ "']//..//following-sibling::td/a[contains(text(),' Início')]"));
		clickElementJS(driver, stratedOrder);
		startOrderButton.click();
		waitForPageToLoad(driver);
		waitForElement(dashboardTitle, 10);
	}

	/**
	 * Check if the edited order was successfully edited
	 * 
	 * @return
	 */
	public boolean checkingEditOrder() {
		String plannedPieces = driver.findElement(By.xpath("//tbody//td[7]/a[1]")).getText();
		boolean isOrderNumberDisplayed = isElementDisplayed(
				By.xpath("//a[normalize-space()='" + dataMap.get("Delivered Order Number Edit") + "']"));

		if (isOrderNumberDisplayed && plannedPieces.equals(dataMap.get("plannedProduction"))) {
			log("Successfully Edited the Order Number: " + dataMap.get("Delivered Order Number Edit"));
			return true;
		}
		logFail("The System was not able to Edit the Order");
		return false;
	}

	/**
	 * Checking if the Order Number is displayed in the Planned orders screen
	 * 
	 * @throws Exception
	 */
	public boolean checkingOrderNumberAndSpecifications() throws Exception {
		String deliveredOrderNumber = dataMap.get("Delivered Order Number");

		boolean isOrderNumberDisplayed = isElementDisplayed(
				By.xpath("//a[normalize-space()='" + deliveredOrderNumber + "']"));

		if (isOrderNumberDisplayed) {
			log("Successfully created the Order Number: " + deliveredOrderNumber);
			return true;
		}
		logFail("The System was not able to create the Order");
		return false;
	}

	/**
	 * End a Order. It is only possible to end a running order
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean endOrder() throws Exception {
		// Getting the Running Order Number
		String startOrderNumber = runningOrderNumber.getText();
		// Ending the running Order
		endOrderButton.click();

		// Checking if the Ended Order is no longer displayed
		if (isElementDisplayed(By.xpath("//strong[normalize-space()='" + startOrderNumber + "']"))) {
			confirmEndOrderButton.click();
			waitForElement(dashboardTitle, 10);

			if (!isElementDisplayed(
					By.xpath("//*[@id='DetailJob']/ul/li[1]/span[contains(text(), '" + startOrderNumber + "')]"))) {
				log("The system successfully ended the order: " + startOrderNumber);
			} else {
				logFail("The system was not able to end the order");
				return false;
			}

		} else {
			logFail("The system was not able to end the order");
			return false;
		}
		return true;
	}

	/**
	 * Delete a Order from the line
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOrder() throws Exception {
		// Creating a new Order
		createNewOrder();

		// Go to edit the previous created order
		WebElement editOrderButton = driver.findElement(By.xpath("//*[@id='TablePlannedOrders']//a[contains(text(),'"
				+ dataMap.get("Delivered Order Number") + "')]/ancestor::tr//a[contains(text(),'Editar')]"));
		clickElementJS(driver, editOrderButton);

		// Deleting the order
		clickElementJS(driver, deleteButton);

		waitForPageToLoad(driver);

		// Checking if the deleted order is no longer displayed in the Line DashBoard
		if (!isElementDisplayed(By.xpath("//a[normalize-space()='" + dataMap.get("Delivered Order Number") + "']"))) {
			log("Successfully Deleted the Order Number: " + dataMap.get("Delivered Order Number"));
			return true;
		}
		logFail("The System was not able to Delete the Order");
		return false;
	}

	/**
	 * This method cancel a running order
	 * 
	 * @return - if the system cancelled the running order
	 * @throws Exception
	 */
	public boolean cancelOrder() throws Exception {
		// If the order was already cancelled
		if (isElementDisplayed(By.id("ButtonMenu_Job_UndoCancel"))) {
			// Undoing the cancel
			undoCancelOrderButton.click();
			waitForPageToLoad(driver);
			confirmatioUndoCancelOrderButton.click();

			// Canceling the Order
			cancelOrderButton.click();
			waitForPageToLoad(driver);
			confirmCancelOrderButton.click();
		} else {

			// If there is a running order
			if (isElementDisplayed(By.xpath("//*[@id='DetailJob']//li[1]/span[2][contains(text(),'O')]"))) {
				// Getting the running order number
				String runningOrder = driver
						.findElement(By.xpath("//*[@id='DetailJob']//li[1]/span[2][contains(text(),'O')]")).getText();
				dataMap.put("RunningOrder", runningOrder);

				// Canceling the Order
				cancelOrderButton.click();
				waitForPageToLoad(driver);
				confirmCancelOrderButton.click();

			} else {
				createNewOrder();
				startOrder();

				// Canceling the Order
				cancelOrderButton.click();
				waitForPageToLoad(driver);
				confirmCancelOrderButton.click();
			}
		}

		// Checking the cancelled order is not anymore displayed
		if (isElementDisplayed(By.xpath(
				"//*[@id='DetailJob']//li[1]/span[2][contains(text(),'" + dataMap.get("RunningOrder") + "')]"))) {
			logFail("The system was not able to cancel a order");
			return false;
		}
		log("The system successfully cancelled a order");
		return true;
	}

	/**
	 * Select a Random Product Type to search
	 */
	public void randomProductType() {
		List<WebElement> products = new ArrayList<WebElement>();
		products = driver.findElements(By.xpath("//*[@id='InputProductPicker']/option"));
		int size = products.size();
		Random random = new Random();
		int randomNumber = random.nextInt(size);
		if (randomNumber == 1 || randomNumber == 0) {
			randomNumber = 2;
		}

		WebElement selectedProduct = driver
				.findElement(By.xpath("//*[@id='InputProductPicker']/option[" + randomNumber + "]"));
		String selectedProductName = selectedProduct.getText();
		selectedProduct.click();
		log("Selected the Product: " + selectedProductName);
		dataMap.put("Product", selectedProductName);
	}

	/**
	 * Select a Random Production Start to a new Order
	 */
	public void randomProductionStart() {
		List<WebElement> productionStart = new ArrayList<WebElement>();
		productionStart = driver.findElements(By.xpath("//select[@id='ddlIsProductionStart']//option"));
		int size = productionStart.size();
		Random random = new Random();
		int randomNumber = random.nextInt(size);
		if (randomNumber == 0) {
			randomNumber = 1;
		}

		WebElement selectedProduction = driver
				.findElement(By.xpath("//select[@id='ddlIsProductionStart']//option[" + randomNumber + "]"));
		String selectedProductionName = selectedProduction.getText();
		selectedProduction.click();
		log("Selected the Production Start as: " + selectedProductionName);
		dataMap.put("ProductionStart", selectedProductionName);
	}

	// Pick a random number greater than 100
	public String randomNumberGreaterThan100() {
		Random random = new Random();
		int low = 100;
		int high = 200;
		int result = random.nextInt(high - low) + low;
		String value = Integer.toString(result);
		dataMap.put("plannedProduction", value);

		return value;
	}

	// Click Save Button
	public void clickingSaveButton() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", saveButton);
		saveButton.click();
		log("Click Save Button");
	}

	// Get the Order Number to compare after the edit
	public void capturingOrderNumberEdit() throws Exception {
		scrollToElementJS(driver, orderNumber);
		dataMap.put("Delivered Order Number Edit", orderNumber.getAttribute("value"));
		log("Captured the Order Number: " + dataMap.get("Delivered Order Number Edit"));
		waitForPageToLoad(driver);
	}

	// Get the Order Number to compare after
	public void capturingOrderNumber() throws Exception {
		scrollToElementJS(driver, orderNumber);
		dataMap.put("Delivered Order Number", orderNumber.getAttribute("value"));
		log("Captured the Order Number: " + dataMap.get("Delivered Order Number"));
		waitForPageToLoad(driver);
	}

	// Get the Line Options to compare after
	public List<String> capturingpossiblesOptions() {
		scheduleButton.click();
		newOrder.click();

		Select productsCombox = new Select(driver.findElement(By.xpath("//select[@id='InputProductPicker']")));
		List<WebElement> productsOptions = new ArrayList<WebElement>();
		productsOptions = productsCombox.getOptions();
		int listSize = productsOptions.size();
		for (int i = 1; i <= listSize - 1; i++) {
			String entireText = productsOptions.get(i).getText();
			String substringText = entireText.substring(0, entireText.lastIndexOf(" ["));
			productOptionText.add(substringText);
		}
		return productOptionText;
	}
}