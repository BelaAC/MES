package com.everis.pages.brewing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class MaterialsPage extends BasePage {

	public MaterialsPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_0")
	protected WebElement openNewBatchButton;

	@FindBy(id = "btnNewBatch_NewBatch")
	protected WebElement newBatchButton;

	@FindBy(id = "txtScadaID")
	protected WebElement scadaID;

	@FindBy(xpath = "//*[@id='MainContentUpdatePanel']//h1")
	protected WebElement title;

	@FindBy(id = "BtnForm_Start")
	protected WebElement startButton;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWHOUSE']")
	protected WebElement brewHouseButton;

	@FindBy(xpath = "//a[normalize-space()='Sala de Brassagem 1']")
	protected WebElement brewHouse1Button;

	@FindBy(xpath = "//div[normalize-space()='Materiais']")
	protected WebElement materialsSections;

	@FindBy(xpath = "//tbody//ancestor::tr//a[contains(text(),'kg')]")
	protected WebElement firstMaterial;

	@FindBy(xpath = "//td[normalize-space()='Falsa']/..")
	protected WebElement falseMaterial;

	@FindBy(xpath = "//a[contains(text(),'Editar')]")
	protected WebElement editMaterial;

	@FindBy(id = "LocationSelect_Filter")
	protected WebElement location;

	@FindBy(id = "LotNumberSelect_Filter")
	protected WebElement lotNumber;

	@FindBy(xpath = "//*[@id='LotNumberSelect_Filter']/option[contains(text(),'Inspecionado')]")
	protected WebElement inspectedLot;

	@FindBy(id = "TxtQuantity")
	protected WebElement quantity;

	@FindBy(id = "BtnAddMaterial_AddInventory")
	protected WebElement addButton;

	@FindBy(xpath = "//a[normalize-space()='Confirmar']")
	protected WebElement confirmButton;

	@FindBy(xpath = "//ul[@class='breadcrumb breadcrumb-tsmain']//li[4]/a")
	protected WebElement fermentationTub;
	
	@FindBy(xpath = "//div[@class='tstile-text-medium'][normalize-space()='Filtração']")
	protected WebElement tankFiltration;
	
	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWFILTER']")
	protected WebElement filtrationButton;

	@FindBy(xpath = "//div[@class='alert alert-tsvalidation alert-danger alert-tsvalidation-item alert-dismissible fade in']")
	protected WebElement alert;

	@FindBy(id = "Form_TimeTransfer_Time")
	protected WebElement transferTime;

	@FindBy(xpath = "//span[contains(text(),'Quantidade dispon')]//following-sibling::span")
	protected WebElement quantityOnHand;

	@FindBy(xpath = "//label[contains(text(),'Material out of planned tolerance')]")
	protected WebElement materialOutPlannedTitle;

	@FindBy(xpath = "//tr[contains(@data-id, '0')]//a[normalize-space()='Editar']")
	protected WebElement editButton;

	@FindBy(id = "InputQuantity")
	protected WebElement editQuantity;

	@FindBy(xpath = "//a[normalize-space()='Salvar']")
	protected WebElement saveButon;

	@FindBy(xpath = "//tr[contains(@data-id, '0')]//td[3]")
	protected WebElement editedQuantity;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWYEAST']")
	protected WebElement yeastManagement;

	@FindBy(xpath = "//div[normalize-space()='Propagador']")
	protected WebElement propagator;

	@FindBy(css = "#UniTanks > a")
	protected WebElement maturation;

	@FindBy(css = "#Fermentation > a")
	protected WebElement fermentation;

	@FindBy(css = "#Filtration > a")
	protected WebElement filtration;

	@FindBy(xpath = "//div[normalize-space()='Regeneração PVPP']")
	protected WebElement pvpp;

	@FindBy(css = "#TileMaterial > div > div.tstile-lower.text-right > div.tstile-text-medium")
	protected WebElement realQuantity;

	List<WebElement> beforeAddedMaterials = new ArrayList<WebElement>();
	Random random = new Random();

	/**
	 * This method does the material management for all possibles equipments
	 * 
	 * @throws Exception
	 */
	public void materialsManagement() throws Exception {
		// BrewHouse Material
		materialManagementBrewHouse();
		// Propagator Material
		materialManagementPropagator();
		// Fermentation Unitank Material
		materialManagementFermentatioUnitank();
		// Maturation Unitank Material
		materialManagementMaturationUnitank();
		// PVPP Material
		materialManagemntPVPP();
	}

	/**
	 * This method does all the material management in the Brew House
	 * 
	 * @throws Exception
	 */
	public void materialManagementBrewHouse() throws Exception {
		// Go to the Brew House 1
		brewHouseButton.click();
		brewHouse1Button.click();

		// Getting the first equipment
		for (int i = 1; i < 2; i++) {
			driver.findElement(By.xpath("//*[@id='BrewHouseStatus_BH1']/a[" + i + "]")).click();
		}
		// Materials Management for BrewHouse
		openEditCancelMaterial(brewHouse1Button, "CERVEJA BRAHMA FABRICACAO MOSTO HG - ( 50008601 )");
	}

	/**
	 * This method does all the material management in the propagator
	 * 
	 * @throws Exception
	 */
	public void materialManagementPropagator() throws Exception {
		// Go to Propagator
		yeastManagement.click();
		clickElementJS(driver, propagator);
		// Materials Management for Propagator
		openEditCancelMaterial(yeastManagement, "ITW263 Propagação - ( 26300 )");
	}

	/**
	 * This method does all the material management in the fermentation tanks
	 * 
	 * @throws Exception
	 */
	public void materialManagementFermentatioUnitank() throws Exception {
		// Materials Management for Fermentation Unitank
		selectRandomFermentationUnitank();
		openEditCancelMaterial(maturation, "BC DUPLO MALTE Fermentação - ( 77850 )");
	}

	/**
	 * This method does all the material management in the maturation tanks
	 * 
	 * @throws Exception
	 */
	public void materialManagementMaturationUnitank() throws Exception {
		// Materials Management for Maturation Unitank
		selectRandomUnitank();
		openEditCancelMaterial(maturation, "BC DUPLO MALTE Fermentação - ( 77850 )");
	}

	/**
	 * This method does all the material management in PVPP
	 * 
	 * @throws Exception
	 */
	public void materialManagemntPVPP() throws Exception {
		// Materials Management for PVPP
		filtration.click();
		pvpp.click();
		openEditCancelMaterial(filtration, "SK Filtração PRE - ( 38631 )");
	}

	/**
	 * This method does all the material management in PVPP
	 * 
	 * @throws Exception
	 */
	public void materialManagemntFermentationTub() throws Exception {
		// Edit Materials for Fermentation Tub
		editMaterial();

		// Cancel Materials for Fermentation Tub
		cancelMaterial();

		// Go to the fermentation Tub Main Screen
		driver.findElement(By.xpath("//a[@title= '" + dataMap.get("FermentationTub") + "']")).click();

	}
	
	public void materialManagementFiltration() throws Exception {
		// Go to Filtration Tank
		filtrationButton.click();
		tankFiltration.click();
		
		// Edit Materials for Filtration
		editMaterial();

		// Cancel Materials for Filtration
		cancelMaterial();
	}

	/**
	 * This method checks if the system successfully edited or cancelled a material
	 * 
	 * @param quantityString
	 * @return - If the system successfully edited a material
	 */
	public boolean checkEditMaterial(String quantityString) {
		// Checking if the edited Quantity was updated
		String editedQuantityString = editedQuantity.getText();
		editedQuantityString = StringUtils.substringBefore(editedQuantityString, " ");

		if (!(editedQuantityString.equalsIgnoreCase(quantityString))) {
			if (quantityString.equalsIgnoreCase("0.00")) {
				logFail("The system was not able to cancel a Material");
			} else {
				logFail("The system was not able to edit a Material");
			}
			return false;
		}
		log("The system successfully edited a Material");
		return true;
	}

	/**
	 * This method cancel a material and checks the cancel 
	 */
	public void cancelMaterial() {
		// Edit the last transfer
		clickElementJS(driver, editMaterial);
		// Get the transfer quantity as 0
		editQuantity.clear();
		editQuantity.sendKeys("0");
		saveButon.click();
		// Checking if the Out of Planned Tolerance Pop-up is Displayed
		wait(2);
		if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
			confirmButton.click();
		}

		// Check the transfer time
		checkTransferTime();

		checkEditMaterial("0");
	}

	/**
	 * This method edits a material
	 */
	public void editMaterial() {
		// Go to materials
		clickElementJS(driver, materialsSections);
		// Edit the last transfer
		clickElementJS(driver, editMaterial);
		// Get the transfer quantity
		String quatity = StringUtils.substringBefore(realQuantity.getText(), " ");
		editQuantity.clear();
		editQuantity.sendKeys(quatity);
		saveButon.click();

		// Checking if the Out of Planned Tolerance Pop-up is Displayed
		wait(2);
		if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
			confirmButton.click();
		}

		// Check the transfer time
		checkTransferTime();

		checkEditMaterial(quatity);
	}

	/**
	 * This method selects a random fermentation tank
	 */
	public void selectRandomFermentationTank() {
		// Go to fermentation
		fermentation.click();
		int randomNum = random.nextInt((12 - 1) + 1) + 1;
		driver.findElement(By.cssSelector("#TilesMainSystems > a:nth-child(" + randomNum + ")")).click();
	}

	/**
	 * This method select a random fermentation unitank
	 */
	public void selectRandomUnitank() {
		// Go to maturation
		maturation.click();
		int randomNum = random.nextInt((9 - 3) + 1) + 3;
		WebElement unitank = driver.findElement(By.cssSelector("#TilesMainSystems > a:nth-child("+randomNum+")"));
		clickElementJS(driver, unitank);
	}

	/**
	 * This method select a random fermentation unitank
	 */
	public void selectRandomFermentationUnitank() {
		// Go to maturation
		maturation.click();
		int randomInt = random.nextInt(3);
		if (randomInt == 0) {
			randomInt = 1;
		}
		// driver.findElement(By.cssSelector("#TilesMainSystems > a:nth-child("+
		// randomInt + ")")).click();
		WebElement unitank = driver.findElement(By.cssSelector("#TilesMainSystems > a:nth-child(9)"));
		clickElementJS(driver, unitank);
	}

	/**
	 * This method returns a random int in the list size range. This method does not
	 * returns 0
	 * 
	 * @param webElementList - A WebElement list
	 * @return - A int greater than zero in the list range
	 */
	public int randomEquipment(List<WebElement> webElementList) {
		Random random = new Random();
		int randomInt = random.nextInt(webElementList.size());
		if (randomInt == 0) {
			randomInt = 1;
		}
		return randomInt;
	}

	/**
	 * This method creates, edit a cancel a material in Brew house
	 * 
	 * @throws Exception
	 */
	public void openEditCancelMaterial(WebElement equipmentArea, String batch) throws Exception {
		// Checking if the equipment has already a running Batch
		if (isElementDisplayed(By.xpath("//div[normalize-space()='Materiais']"))) {

			// Checking if the material remaining is 0
			// If the material remaining is equal to 0 there are two options
			if (isElementDisplayed(By.xpath("//*[@id='JobProgressTiles']/a[1]//div[contains(text(),'0 Restante')]"))) {
				clickElementJS(driver, materialsSections);

				// The first option is to not have any Material Usage Record, so there is no way
				// to test
				boolean noMaterialUsageRecord = isElementDisplayed(
						By.xpath("//p[normalize-space()='Nenhum consumo de material registrado']"));
				if (noMaterialUsageRecord) {
					// Go to the Equipment Area
					clickElementJS(driver, equipmentArea);
				} else {
					// The second option is if the BlueBox integration already added a material so
					// is possible to edit and cancel
					// Editing the created Material
					editMaterial("100,00");

					// Canceling the created Material
					editMaterial("0,00");

					// Go to the Equipment Area
					clickElementJS(driver, equipmentArea);
				}
			} else {
				// Adding a new Material
				addMaterial();

				// Editing the created Material
				editMaterial("100,00");

				// Canceling the created Material
				editMaterial("0,00");

				// Go to the Equipment Area
				clickElementJS(driver, equipmentArea);
			}
		} else {
			if (!isFiltoPrensaOrTanqueIntermediario()) {
				openNewBatchButton.click();
				newBatchButton.click();

				// Creating a New HG Batch
				newBatch(batch, driver);

				// Starting the Batch
				startBatch();

				// Adding a new Material
				addMaterial();

				// Editing the created Material
				editMaterial("100,00");

				// Canceling the created Material
				editMaterial("0,00");

				// Go to the Equipment Area
				clickElementJS(driver, equipmentArea);
			} else {
				// Go to the Equipment Area
				clickElementJS(driver, equipmentArea);
			}
		}

	}

	public boolean isFiltoPrensaOrTanqueIntermediario() {
		String equipment = title.getText();
		equipment = StringUtils.substringAfter(equipment, "- ");

		if (equipment.equalsIgnoreCase("Filtro Prensa") || equipment.equalsIgnoreCase("Tanque Intermediário")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method adds a material into the added materials and checks if it was
	 * correctly added
	 * 
	 * @throws Exception
	 */
	public void addMaterial() throws Exception {
		clickElementJS(driver, materialsSections);
		if (isElementDisplayed(By.xpath("//tbody//ancestor::tr//a[contains(text(),'kg')]"))) {
			firstMaterial.click();

			waitForPageToLoad(driver);

			// Getting how many added materials there are
			beforeAddedMaterials = driver.findElements(By.xpath("//tbody[contains(@class,'AbiGridPartBody')]//tr"));

			// Selecting a random Location
			selectRandomLocation();

			// Selecting a Inspected Lot
			lotNumber.click();
			if (isElementDisplayed(
					By.xpath("//*[@id='LotNumberSelect_Filter']/option[contains(text(),'Inspecionado')]"))) {
				inspectedLot.click();
			} else {
				selectRandomLocation();
			}

			// Input the Planned Quantity in Quantity
			quantity.sendKeys(plannedQuantity());
			clickElementJS(driver, addButton);
			wait(1);

			// Changing the Quantity if the Planned Quantity is greater than the Quantity on
			// Hand
			if (isElementDisplayed(By.cssSelector("div.modal-header > label"))) {
				confirmButton.click();
				String onHandQuantity = quantityOnHand.getText();

				// Input on Hand Quantity in Quantity
				quantity.clear();
				quantity.sendKeys(StringUtils.substringBefore(onHandQuantity, " kg"));
				clickElementJS(driver, addButton);
				wait(1);
			}

			// Checking if the Out of Planned Tolerance Pop-up is Displayed
			if (isElementDisplayed(By.cssSelector("div.modal-header > label"))) {
				confirmButton.click();
			}

			// Checking if there is enough material
			if (isElementDisplayed(By.id("ValidationMaterials"))) {
				// Input 1 in Quantity
				quantity.clear();
				quantity.sendKeys("1");
				addButton.click();

				// Checking if the Out of Planned Tolerance Pop-up is Displayed
				if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
					confirmButton.click();
				}

				// Checking if the Out of Planned Tolerance Pop-up is Displayed
				if (isElementDisplayed(By.id("FormValidation"))) {
					String ultimaPalavra = alert.getText();
					int index = ultimaPalavra.lastIndexOf(" ");
					ultimaPalavra = (ultimaPalavra.substring(index + 1));
					confirmButton.click();
				}
			}

			wasMaterialAdded();
		} else {
			// Go to edit the material
			falseMaterial.click();
			clickElementJS(driver, editMaterial);
			waitForPageToLoad(driver);

			// Input 1 in Quantity
			editQuantity.clear();
			editQuantity.sendKeys("1");
			saveButon.click();

			// Checking if the Out of Planned Tolerance Pop-up is Displayed
			if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
				confirmButton.click();
			}
		}
	}

	/**
	 * This method edits a Material by changing the materials Quantity to the one
	 * desired
	 * 
	 * @param quantityString - The value to be input into the new Quantity
	 * @return
	 */
	public boolean editMaterial(String quantityString) {
		// Checking if the Out of Planned Tolerance Pop-up is Displayed
		wait(2);
		if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
			confirmButton.click();
		}

		checkTransferTime();

		// Editing the MaterialQuantity to 100
		clickElementJS(driver, editButton);
		editQuantity.clear();
		editQuantity.sendKeys(quantityString);
		saveButon.click();

		// Checking if the Out of Planned Tolerance Pop-up is Displayed
		wait(2);
		if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
			confirmButton.click();
		}

		// Checking if there is enough quantity
		if (isElementDisplayed(By.id("FormValidation"))) {
			editQuantity.clear();
			editQuantity.sendKeys("1");
			saveButon.click();
			quantityString = "1,00";

			// Checking if the Out of Planned Tolerance Pop-up is Displayed
			wait(2);
			if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
				confirmButton.click();
			}

			checkTransferTime();
		}

		// Checking if the edited Quantity was updated
		String editedQuantityString = editedQuantity.getText();
		editedQuantityString = StringUtils.substringBefore(editedQuantityString, " ");
		switch (editedQuantityString) {
		case "0":
			editedQuantityString = "0,00";
			break;
		case "100":
			editedQuantityString = "100,00";
			break;

		default:
			break;
		}

		if (!(editedQuantityString.equalsIgnoreCase(quantityString))) {
			if (quantityString.equalsIgnoreCase("0.00")) {
				logFail("The system was not able to cancel a Material");
			} else {
				logFail("The system was not able to edit a Material");
			}
			return false;
		}
		if (quantityString.equalsIgnoreCase("0.00")) {
			log("The system successfully cancelled a Material");
		} else {
			log("The system successfully edited a Material");
		}
		return true;
	}

	public void checkTransferTime() {
		if (isElementDisplayed(By.id("FormValidation"))) {
			String startTime = alert.getText();
			startTime = startTime.substring(startTime.lastIndexOf(" ") + 1);
			transferTime.clear();
			transferTime.sendKeys(startTime);
			saveButon.click();
			// Checking if the Out of Planned Tolerance Pop-up is Displayed
			wait(2);
			if (isElementDisplayed(By.xpath("//label[contains(text(),'Material fora da tolerância planejada')]"))) {
				confirmButton.click();
			}
		}
	}

	/**
	 * This method select a random QoH Location
	 * 
	 * @throws Exception
	 */
	public void selectRandomLocation() throws Exception {
		location.click();
		driver.findElement(
				By.xpath("//*[@id='LocationSelect_Filter']/option[contains(text(),'QoH')][" + randomNumber() + "]"))
				.click();
		waitForPageToLoad(driver);
	}

	/**
	 * This method gets the Materials Planned Quantity and returns only the numbers
	 * with no weight measurements
	 * 
	 * @return - The Materials Planned Quantity without weight measurements
	 */
	public String plannedQuantity() {
		String plannedQuantity = firstMaterial.getText();
		plannedQuantity = StringUtils.substringBefore(plannedQuantity, ",");
		return plannedQuantity;
	}

	/**
	 * This method gets the Location Options and selects a Random Number into the
	 * Locations options range. Do not change this into a Select, the page refresh
	 * it self
	 * 
	 * @return - A random number in Location Options range
	 */
	public int randomNumber() {
		List<WebElement> locationOptions = new ArrayList<WebElement>();
		locationOptions = driver
				.findElements(By.xpath("//*[@id='LocationSelect_Filter']/option[contains(text(),'QoH')]"));
		Random random = new Random();
		int randomNumber = random.nextInt(locationOptions.size());
		if (randomNumber == 0) {
			randomNumber = 1;
		}
		return randomNumber;
	}

	/**
	 * This method checks if the added material was correctly added
	 * 
	 * @return - If the Planned Quantity of the previous Added Material is Displayed
	 * @throws Exception
	 */
	public boolean wasMaterialAdded() throws Exception {
		wait(5);
		List<WebElement> afterAddedMaterials = new ArrayList<WebElement>();
		afterAddedMaterials = driver.findElements(By.xpath("//tbody[contains(@class,'AbiGridPartBody')]//tr"));

		if (beforeAddedMaterials.size() > afterAddedMaterials.size()
				|| beforeAddedMaterials.size() == afterAddedMaterials.size()) {
			logFail("The system was not able to add a Material");
			return false;
		}
		log("The system successfully added a Material");
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
		if (isElementDisplayed(By.id("txtScadaID"))) {
			scadaID.sendKeys("8888");
		}
		startButton.click();

		// Waiting the Batch to Start
		waitForElement(title, 5);

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
}