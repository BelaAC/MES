package com.everis.pages.brewing;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class BHMillPage extends BasePage {

	public BHMillPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWHOUSE']")
	protected WebElement brewHouseButton;

	@FindBy(id = "MainBH1_ML001")
	protected WebElement brewHouse1Button;

	@FindBy(xpath = "//a[normalize-space()='Final']")
	protected WebElement endButton;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_0")
	protected WebElement openNewBatchButton;

	@FindBy(xpath = "//a[normalize-space()='Nova Ordem']")
	protected WebElement newBatchButton;

	@FindBy(xpath = "//a[normalize-space()='Salvar']")
	protected static WebElement saveButton;

	@FindBy(xpath = "//*[@id='BrewHouseStatus_BH1']/a[1]")
	protected WebElement firstEquipment;

	@FindBy(id = "btnSaves_Save")
	protected WebElement saveEndButton;

	@FindBy(id = "txtScadaID")
	protected WebElement scadaID;

	@FindBy(xpath = "//span[contains(text(),'Sala de Brassagem')]")
	protected WebElement title;

	@FindBy(id = "BtnForm_Start")
	protected WebElement startButton;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_2")
	protected WebElement cancelBatchButton;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_4")
	protected WebElement editButton;

	@FindBy(id = "btnCancel_CancelBatch")
	protected WebElement secondCancelBatchButton;

	@FindBy(id = "contentPage_FormValidation_Warning_Button")
	protected WebElement forceCancelButton;

	@FindBy(xpath = "//a[normalize-space()='Transferir']")
	protected WebElement transferOutButton;

	@FindBy(id = "BatchName")
	protected WebElement batchName;

	@FindBy(xpath = "//span[normalize-space()='Tamanho do Lote']//following-sibling::span")
	protected WebElement batchSize;

	@FindBy(id = "TxtQuantity")
	protected WebElement quantity;

	@FindBy(id = "BtnTransferCancel_Transfer")
	protected WebElement transferButton;

	@FindBy(xpath = "//*[@id='MainContentUpdatePanel']/div[5]//span")
	protected WebElement equipmentName;

	@FindBy(id = "ddlProcessArea")
	protected WebElement processAreaCombobox;

	@FindBy(id = "ddlTransferLocations")
	protected WebElement transferLocationsCombobox;

	@FindBy(xpath = "//select[@id='ddlTransferLocations']//option[contains(text(),'Inativo')]")
	protected WebElement inactiveLocationOption;

	@FindBy(xpath = "//a//div[contains(text(),'Whirpool')]")
	protected WebElement whirpoolButton;

	@FindBy(css = "a[href^='/TS/pages/abi/brewing/BLENDTOOL']")
	protected WebElement blendtoolButton;

	@FindBy(id = "FilterProcessAreas")
	protected WebElement processAreaFilterCombobox;

	@FindBy(xpath = "//div[@id='FilterSytems']//span[normalize-space('Check All')]")
	protected WebElement checkAllSystems;

	@FindBy(xpath = "//div[@id='FilterProducts']//span[normalize-space('Check All')]")
	protected WebElement checkAllProducts;

	@FindBy(id = "LoadBatches_LoadBatches")
	protected WebElement loadBatchesButton;

	@FindBy(css = "#JobProgressTilesQuality > a")
	protected WebElement batchQualityButton;

	@FindBy(css = "#gvBatchs > tbody > tr:nth-child(1) > th:nth-child(10)")
	protected WebElement parameter;

	@FindBy(css = "#tsModalDialogInternalFooter > button")
	protected WebElement closePopUpButton;

	@FindBy(id = "btnSaves_Close")
	protected WebElement backButton;

	@FindBy(xpath = "// *[@id='SaveValidator']//button")
	protected WebElement warningBackButton;

	@FindBy(id = "BlendButtons_Calculate")
	protected WebElement blendCalculateButton;

	@FindBy(xpath = "//*[@id='gvBlendResult']//td[1]/span")
	protected WebElement resultBlendSize;

	@FindBy(id = "txtBatchSize")
	protected WebElement editBatchSize;

	int equipmentSize = 0;

	/**
	 * This method check if there is any running batch in any equipment in the
	 * system, if so it ends the batch so the test has a clean workspace
	 * 
	 * @throws Exception
	 */
	public void checkRunnigOrders() throws Exception {
		// Cleaning the Workspace by closing all running Batch in every equipment
		cleanWorkspace();

		// Opening a new Batch in the first Equipment
		firstEquipment.click();
		openNewBatchButton.click();
		newBatchButton.click();

		// Creating a New HG Batch
		newBatch("CERVEJA BRAHMA FABRICACAO MOSTO HG - ( 50008601 )", driver);
		log("The system sucessfully created a new Batch");

		// Checking the if the Batch was successfully created
		checkingBatchNumberAndProduct();
	}

	/**
	 * This method check if there is any running batch in any equipment in the
	 * system, if so it ends the batch so the test has a clean workspace
	 * 
	 * @throws Exception
	 */
	public void newCIPBatch(String processArea) throws Exception {
		driver.findElement(By.cssSelector("#" + processArea + " > a")).click();
		String id = null;

		switch (processArea) {
		case "BrewHouse":
			id = "BrewHouseStatus_BH1";
			break;

		default:
			break;
		}
		// Cleaning the Workspace by closing all running Batch in every equipment
		cleanCIPWorkspace(id, processArea);

		// Opening a new Batch in the first Equipment
		List<WebElement> equipments = new ArrayList<WebElement>();
		equipments = driver.findElements(By.cssSelector("#" + id + " > a"));

		for (int i = 2; i < equipments.size(); i++) {
			driver.findElement(By.cssSelector("#" + id + " > a:nth-child(" + i + ")")).click();

			// Opening a new Batch in the Equipment
			openNewBatchButton.click();
			newBatchButton.click();

			newBatch("CIP", driver);
			log("The system sucessfully created a new CIP Batch");

			startBatch();

			// Checking the if the CIP Batch was successfully created
			checkingCIPPTPs();

			// Go back to the process Area. Ask to the driver to look for it again otherwise
			// it gets a stale exception
			driver.findElement(By.cssSelector("#" + processArea + " > a")).click();
		}
	}

	/**
	 * This method edit a existing Batch by changing its Product
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean editBatch() throws Exception {
		// Go to edit
		editButton.click();

		// Editing the Batch
		String bacthSizeValue = editBatchSize.getAttribute("value");
		int batchSizeNumber = Integer.parseInt(bacthSizeValue);
		batchSizeNumber = batchSizeNumber / 2;
		bacthSizeValue = String.valueOf(batchSizeNumber);

		// Editing the Batch
		editBatchSize.clear();
		editBatchSize.sendKeys(bacthSizeValue);

		// Finish editing
		saveEndButton.click();
		clickElementJS(driver, forceCancelButton);
		closePopUpButton.click();
		backButton.click();

		// Getting only the numbers
		String batchSizeString = StringUtils.substringBefore(batchSize.getText(), " ");

		// Checking the edited Batch
		if (batchSizeString.equalsIgnoreCase(bacthSizeValue)) {
			log("The system sucessfully edited a Batch");
		} else {
			logFail("The system was not able to edit a batch");
			return false;
		}
		return true;
	}

	/**
	 * This method goes to the BlendTool and select the given process area and
	 * checks all the systems and products
	 * 
	 * @param processArea - One of the brewing process areas, usually areas like
	 *                    maturation and filtration
	 */
	public void goToBlendTool(String processArea) {
		// Go to BlendTool
		clickElementJS(driver, blendtoolButton);

		if (processArea.equalsIgnoreCase("Maturação")) {
			processAreaFilterCombobox.click();
			driver.findElement(By.xpath("//*[@id='FilterProcessAreas']/option[contains(text(),'Maturação')][2]"))
					.click();

		} else {
			// Selecting the process Area in the Process Filter
			Select processAreaFilter = new Select(processAreaFilterCombobox);
			processAreaFilter.selectByVisibleText(processArea);
		}

		// Selecting All Systems and Products
		checkAllSystems.click();
		checkAllProducts.click();

		// Clicking to load the batches
		clickElementJS(driver, loadBatchesButton);

		dataMap.put("ProcessArea", processArea);
	}

	/**
	 * This method select two batches and goes to the batch's quality and insert a
	 * value into the parameter to blend
	 */
	public void selectBatchesBlendTool() {
		// Selecting two batches
		selectTwoBatches();
		// Go to the batch quality and input a value into the first batch parameter
		selectBatchNumber("0");
		// Go back to blendTool to take the second batch
		goToBlendTool(dataMap.get("ProcessArea"));
		// Go to the batch quality and input a value into the second batch parameter
		selectBatchNumber("1");
		// Go back to blendTool to select the batches
		goToBlendTool(dataMap.get("ProcessArea"));
		// Get the batch size to compare later
		getBatchHL();
		// Get the parameter value
		getParameterValue();
		// Selecting the two Batches
		driver.findElement(By.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber0") + "']/ancestor::tr//input"))
				.click();
		driver.findElement(By.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber1") + "']/ancestor::tr//input"))
				.click();
		clickElementJS(driver, blendCalculateButton);
		log("Successfully selected two batches");
	}

	/**
	 * This method gets the parameter value to compare later with the calculation
	 * result
	 */
	public void getParameterValue() {
		// Getting the parameter value
		String parameterValue0 = driver
				.findElement(
						By.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber0") + "']/ancestor::tr/td[10]"))
				.getText();
		// Saving only the parameter number value to compare later
		dataMap.put("ParameterValue0", StringUtils.substringBefore(parameterValue0, " "));
		// Getting the parameter value
		String parameterValue1 = driver
				.findElement(
						By.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber1") + "']/ancestor::tr/td[10]"))
				.getText();
		// Saving only the parameter number value to compare later
		dataMap.put("ParameterValue1", StringUtils.substringBefore(parameterValue1, " "));
	}

	/**
	 * This method gets the Batch size to compare later with the calculation result
	 */
	public void getBatchHL() {
		dataMap.put("Batch0Value",
				driver.findElement(By
						.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber0") + "']/ancestor::tr/td[9]/input"))
						.getAttribute("value"));
		dataMap.put("Batch1Value",
				driver.findElement(By
						.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber1") + "']/ancestor::tr/td[9]/input"))
						.getAttribute("value"));
	}

	/**
	 * This method goes to the batch and input the target value into parameter
	 * 
	 * @param orderNumber - The Order Number can be 0 or 1
	 */
	public void selectBatchNumber(String orderNumber) {
		// Getting the selected parameter to change in the batch quality
		String parameterString = parameter.getText();
		dataMap.put("Parameter", StringUtils.substringBefore(parameterString, " ["));
		dataMap.put("ParameterID",
				parameterString.substring(parameterString.indexOf("[") + 1, parameterString.indexOf("]")));

		// Go to the Batch Quality
		driver.findElement(By.xpath("//a[normalize-space()='" + dataMap.get("OrderNumber" + orderNumber) + "']"))
				.click();
		clickElementJS(driver, batchQualityButton);

		// Getting the target value for the parameter
		String target = driver
				.findElement(By.xpath("//a[contains(text() , '" + dataMap.get("Parameter") + "')]/ancestor::tr/td[13]"))
				.getText();
		WebElement inputValue = driver.findElement(
				By.xpath("//a[contains(text() , '" + dataMap.get("Parameter") + "')]/ancestor::tr/td[14]/input"));

		// Inputing the target into the value and pressing Enter
		inputValue.clear();
		inputValue.sendKeys(target);
		inputValue.sendKeys(Keys.ENTER);

		// Waiting to check if the Warning is displayed
		wait(2);
		if (isElementDisplayed(By.cssSelector("#tsModalDialogInternal > div > div"))) {
			warningBackButton.click();
		}
	}

	/**
	 * This method select two batches with size greater than 0 in BlendTool
	 */
	public void selectTwoBatches() {
		List<String> batchesSizesString = new ArrayList<String>();
		List<WebElement> batchesSizes = new ArrayList<WebElement>();
		// Getting all the batches size web elements and saving into the list
		batchesSizes = driver.findElements(By.id("BatchSize"));

		// For each element in the batchSize list get the text and verify if it is
		// greater than 0
		for (WebElement webElement : batchesSizes) {
			if (!webElement.getText().equalsIgnoreCase("0 HL")) {
				batchesSizesString.add(webElement.getText());
			}
		}

		// Selecting the two batches with size greater than 0
		for (int i = 0; i <= 1; i++) {
			// Getting the Order Number
			dataMap.put("OrderNumber" + i,
					driver.findElement(
							By.xpath("//span[contains(text(),'" + batchesSizesString.get(i) + "')]//ancestor::tr//a"))
							.getText());
		}
	}

	/**
	 * This method verify if the previous CIP created Batch is generating PTPs
	 * 
	 * @return - If the PTPs are being created
	 * @throws Exception
	 */
	public boolean checkingCIPPTPs() throws Exception {
		waitForPageToLoad(driver);
		wait(10);
		// Getting the process remaining PTPs
		String processPTPs = driver.findElement(By.xpath("//*[@id='JobProgressTilesProcess']//div[1]/div[3]"))
				.getText();
		processPTPs = StringUtils.substringBefore(processPTPs, " ");

		// Getting the periodical remaining PTPs
		String periodicalPTPs = driver.findElement(By.xpath("//*[@id='JobProgressTilesPeriodical']//div[2]/div[3]"))
				.getText();
		periodicalPTPs = StringUtils.substringBefore(periodicalPTPs, " ");

		// Checking if the remaining are equal to 0
		if (processPTPs.equalsIgnoreCase("0") || periodicalPTPs.equalsIgnoreCase("0")) {
			logFail("The system was not able to create or edit a CIP Batch");
			return false;
		}
		return true;
	}

	/**
	 * This method verify if the previous created Batch Number and product is
	 * displayed to the user
	 * 
	 * @return - if Batch Number is Displayed
	 */
	public boolean checkingBatchNumberAndProduct() {
		boolean isBatchNumberAndProductDisplayed = isElementDisplayed(
				By.xpath("//a[normalize-space()='" + dataMap.get("BatchNumber")
						+ "']//ancestor::tr//a[contains(text(),'" + dataMap.get("Product") + "')]"));

		if (!isBatchNumberAndProductDisplayed) {
			logFail("The system was not able to create or edit a new Batch");
			return false;
		}
		return true;
	}

	/**
	 * This method starts the previous created batch and verifies if the Batch
	 * Number is displayed in the running batch Screen
	 * 
	 * @return
	 */
	public boolean startBatch() {
		// Starting a Batch
		WebElement startBatch = driver.findElement(
				By.xpath("//a[normalize-space()='" + dataMap.get("BatchNumber") + "']//ancestor::tr//td[11]/a"));
		clickElementJS(driver, startBatch);

		// Inputing a number in the Scada ID
		scadaID.sendKeys("8888");
		startButton.click();

		// Waiting the Batch to Start
		wait(5);

		// Checking if the started Batch Number is Displayed in the Running Batch Page
		boolean isBatchNumberDisplayed = isElementDisplayed(
				By.xpath("//h2[normalize-space()='" + dataMap.get("BatchNumber") + "']"));

		if (!isBatchNumberDisplayed) {
			logFail("The system was not able to start a Batch");
			return false;
		}
		log("The system successfully started a Batch");
		return true;
	}

	/**
	 * This method closes a running batch
	 * 
	 * @return - if there is any active batch
	 */
	public boolean closeBatch() {
		// Closing the Batch
		endButton.click();
		saveEndButton.click();

		// Checking if there is any active Batch
		boolean isActiveBatchDisplayed = isElementDisplayed(By.id("NoBatchNumber"));
		if (isActiveBatchDisplayed) {
			log("The system sucessfully Closed a Batch");
		} else {
			logFail("The system was not able to Close a Batch");
			return false;
		}
		return true;
	}

	/**
	 * This method cancel a running Batch in a equipment
	 * 
	 * @return - if the batch was successfully cancelled
	 */
	public boolean cancelBatch() {
		// Canceling the running Batch
		cancelBatchButton.click();
		secondCancelBatchButton.click();

		// Forcing Cancel if the Warning Pop-up is displayed
		boolean isWarningDisplayed = isElementDisplayed(By.xpath("//label[normalize-space()='Warning']"));
		if (isWarningDisplayed) {
			forceCancelButton.click();
		}

		// Checking if the running Batch was cancelled
		boolean isCancelledBatchDisplayd = isElementDisplayed(By.xpath("//div[contains(text(), 'Lote Cancelado')]"));
		if (isCancelledBatchDisplayd) {
			log("The system successfully cancelled a running batch");
		} else {
			logFail("The system was not able to cancel a batch");
			return false;
		}
		return true;
	}

	/**
	 * This method cleans the workspace by closing all the running batches in every
	 * equipment
	 */
	public void cleanWorkspace() {
		// Go to the Brew House 1
		brewHouseButton.click();
		brewHouse1Button.click();

		// Checking if any equipment is being used
		List<WebElement> equipments = new ArrayList<WebElement>();
		equipments = driver.findElements(By.xpath("//*[@id='BrewHouseStatus_BH1']/a"));
		equipmentSize = equipments.size();

		for (int i = 1; i <= equipmentSize; i++) {
			String runningOrder = driver.findElement(By.xpath("//a[" + i + "]//div[1]//div[2]//div[2]")).getText();

			if (!(runningOrder.equalsIgnoreCase("Nenhum"))) {
				// Ending running Batch
				driver.findElement(By.xpath("//div[@id='BrewHouseStatus_BH1']/a[" + i + "]")).click();
				closeBatch();
				// Go to the Brew House 1
				brewHouseButton.click();
				brewHouse1Button.click();
			}
		}
	}

	// TODO
	/**
	 * This method cleans the workspace by closing all the running batches in every
	 * equipment
	 */
	public void cleanCIPWorkspace(String id, String processArea) {
		// Checking if any equipment is being used
		List<WebElement> equipments = new ArrayList<WebElement>();
		equipments = driver.findElements(By.xpath("//*[@id='" + id + "']/a"));
		equipmentSize = equipments.size();

		for (int i = 1; i <= equipmentSize; i++) {
			String runningOrder = driver.findElement(By.xpath("//a[" + i + "]//div[1]//div[2]//div[2]")).getText();

			if (!(runningOrder.equalsIgnoreCase("Nenhum"))) {
				// Ending running Batch
				driver.findElement(By.xpath("//div[@id='" + id + "']/a[" + i + "]")).click();
				closeBatch();
				// Go to the process area
				driver.findElement(By.cssSelector("#" + processArea + " > a")).click();
			}
		}
	}

	public boolean transferOutAllEquipments() throws Exception {
		// Opening a new batch in the first Equipment
		checkRunnigOrders();

		// Starting a Batch in the first equipment
		startBatch();

		for (int i = 1; i <= equipmentSize; i++) {
			// Getting the Equipment Name
			String equipmentNameString = equipmentName.getText();
			equipmentNameString = StringUtils.substringAfter(equipmentNameString, "- ");
			dataMap.put("EquipmentName", equipmentNameString);

			// Clicking the Transfer Out
			transferOutButton.click();

			// Saving the Running Batch Number to compare later
			dataMap.put("BatchNumber", batchName.getText());

			// Getting only the Quantity Numbers
			String batchSizeString = batchSize.getText();
			batchSizeString = StringUtils.substringBefore(batchSizeString, " HL");

			// Inputing the batch size into quantity
			quantity.clear();
			quantity.sendKeys(batchSizeString);

			if (i == equipmentSize) {
				for (int j = 2; j <= 4; j++) {
					whirpoolTransfer(j);

					waitForPageToLoad(driver);
					// Go to the Brew House 1
					brewHouseButton.click();
					brewHouse1Button.click();

					// Go to Whirpool
					whirpoolButton.click();

					// Creating a New HG Batch
					openNewBatchButton.click();
					newBatchButton.click();
					newBatch("CERVEJA BRAHMA FABRICACAO MOSTO HG - ( 50008601 )", driver);

					// Starting a Batch
					startBatch();
				}

			} else {
				// Transferring the Running Batch to the next equipment
				clickElementJS(driver, transferButton);

				// Waiting for the title to be visible
				waitElement(title, 10);

				boolean isBatchNumberDisplayed = isElementDisplayed(
						By.xpath("//h2[normalize-space()='" + dataMap.get("BatchNumber") + "']"));

				if (!isBatchNumberDisplayed) {
					logFail("The system was not able to transfer from: " + dataMap.get("EquipmentName"));
					return false;
				}
			}
			log("The system successfully transferred the batch from: " + dataMap.get("EquipmentName"));
		}
		return true;
	}

	/**
	 * This method makes a Whirpool Transfer by selecting the chosen Process Area
	 * and a inactive option for Destination
	 * 
	 * @param processArea - Selected Process Area
	 * @return - if the Process Area is the same as the Transferred Out Area
	 * @throws Exception
	 */
	public boolean whirpoolTransfer(int number) throws Exception {
		// Clicking the Transfer Out
		transferOutButton.click();

		// Saving the Running Batch Number to compare later
		dataMap.put("BatchNumber", batchName.getText());

		// Getting only the Quantity Numbers
		String batchSizeString = batchSize.getText();
		batchSizeString = StringUtils.substringBefore(batchSizeString, " HL");

		// Inputing the batch size into quantity
		quantity.clear();
		quantity.sendKeys(batchSizeString);

		// Selecting a process area
		processAreaCombobox.click();
		WebElement selectedProcess = driver.findElement(By.xpath("//*[@id='ddlProcessArea']/option[" + number + "]"));

		// Getting the selected process name to compare after
		String selectedProcessString = selectedProcess.getText();
		selectedProcessString = StringUtils.substringAfter(selectedProcessString, "- ");
		dataMap.put("ProcessArea", selectedProcessString);
		selectedProcess.click();

		if (!(selectedProcessString.equalsIgnoreCase("Yeast Propagation"))) {
			// Selecting a Inactive Destination Location. Do not change this into a select
			// otherwise it's impossible to chose the inactive option
			transferLocationsCombobox.click();
			inactiveLocationOption.click();
		}

		// Transferring the Running Batch to the next equipment
		clickElementJS(driver, transferButton);

		waitForPageToLoad(driver);
		boolean isProcessAreaDisplayed = isElementDisplayed(
				By.xpath("//span[contains(text(),'" + dataMap.get("ProcessArea") + "')]"));
		if (!isProcessAreaDisplayed) {
			logFail("The system was not able to transfer from: " + dataMap.get("EquipmentName"));
			return false;
		}
		log("The system was able to transfer from: " + dataMap.get("EquipmentName") + " to the Process Area: "
				+ dataMap.get("ProcessArea"));
		return true;
	}

	public boolean verifyBlend() {
		// Getting only the numbers in the blend size result
		String blendSizeResult = resultBlendSize.getText();
		blendSizeResult = StringUtils.substringBefore(blendSizeResult, " ");

		// Turning string into floats
		float blendSize = Float.parseFloat(blendSizeResult.replaceAll(",", "."));
		float batch0Size = Float.parseFloat(dataMap.get("Batch0Value").replaceAll(",", "."));
		float batch1Size = Float.parseFloat(dataMap.get("Batch1Value").replaceAll(",", "."));

		// Getting only the numbers in the parameter result
		String parameterResult = driver
				.findElement(By.xpath("//table[@id='gvBlendResult']//*[@id='" + dataMap.get("ParameterID") + "']"))
				.getText();
		parameterResult = StringUtils.substringBefore(parameterResult, " ");

		// Turning string into floats
		float parameter = Float.parseFloat(parameterResult.replaceAll(",", "."));
		float parameter0 = Float.parseFloat(dataMap.get("ParameterValue0").replaceAll(",", "."));
		float parameter1 = Float.parseFloat(dataMap.get("ParameterValue1").replaceAll(",", "."));

		// Calculating the final parameter result
		float finalResult = ((parameter0 * batch0Size) + (parameter1 * batch1Size)) / blendSize;

		// Rounding uo and formatting the batch size
		DecimalFormat df = new DecimalFormat("##.##");
		df.setRoundingMode(RoundingMode.UP);
		float batchSize = batch0Size + batch1Size;
		String formattedBatchSize = df.format(batchSize);
		float batchSizeReturn = Float.parseFloat(formattedBatchSize.replaceAll(",", "."));

		// Checking if the batch size and the parameter are correct
		if (blendSize == batchSizeReturn && parameter == finalResult) {
			log("The blendtool is successfully working");
		} else {
			logFail("The blendtool is not working correctly");
			return false;
		}
		return true;
	}
}