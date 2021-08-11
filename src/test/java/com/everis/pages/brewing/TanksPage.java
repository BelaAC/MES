package com.everis.pages.brewing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class TanksPage extends BasePage {

	public TanksPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/']")
	protected WebElement brewingButton;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_2")
	protected WebElement endButton;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_1")
	protected WebElement closeOrderButton;

	@FindBy(id = "btnCancel_CancelBatch")
	protected WebElement saveEndButton;

	@FindBy(id = "contentPage_FormValidation_Warning_Button")
	protected WebElement warningButton;

	@FindBy(id = "BtnForm_Start")
	protected WebElement startButton;

	@FindBy(xpath = "//*[@id='MainContentUpdatePanel']/div[5]/div[1]//h1/span")
	protected WebElement title;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWYEAST']")
	protected WebElement yeastManagement;

	@FindBy(id = "MainYEAST_YP001")
	protected WebElement yeastPropagation;

	@FindBy(xpath = "//*[@id='MainYEAST_YP001']/a")
	protected WebElement propagator;

	@FindBy(xpath = "//*[@id='YeastPropagationTiles']/a[1]/div")
	protected WebElement propagatorTank;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_1")
	protected WebElement endOrder;

	@FindBy(id = "MenuOrderOptionsButtons_tsbtnitem_0")
	protected WebElement newBatch;

	@FindBy(id = "btnNewBatch_NewBatch")
	protected WebElement newBatchButton;

	@FindBy(id = "btnSaves_Save")
	protected WebElement saveCancelButton;

	@FindBy(xpath = "//a[normalize-space()='Transferir']")
	protected WebElement transferButton;

	@FindBy(xpath = "//a[contains(text(),'Tanque para Tanque')]")
	protected WebElement tankToTankButton;

	@FindBy(xpath = "//a[contains(text(),'Re-Filtração')]")
	protected WebElement refilterButton;

	@FindBy(id = "YeastCultureCode")
	protected WebElement yeastCultureCode;

	@FindBy(xpath = "//*[@id='BatchHeaderInfo']/ul/li[1]/span[2]")
	protected WebElement yeastCultureSizeKG;

	@FindBy(id = "TxtQuantity")
	protected WebElement quantity;

	@FindBy(id = "ddlProcessArea")
	protected WebElement processAreaCombobox;

	@FindBy(id = "ddlTransferLocations")
	protected WebElement transferLocation;

	@FindBy(id = "BtnTransferCancel_Transfer")
	protected WebElement transferCancelButton;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWFERM']")
	protected WebElement fermentationButton;

	@FindBy(id = "MenuTransferOptionsButton_tsbtnitem_2")
	protected WebElement recoveryYeastButton;

	@FindBy(id = "MenuTransferOptionsButton_tsbtnitem_1")
	protected WebElement transferRecoveryYeastButton;

	@FindBy(id = "MenuTransferOptionsButton_tsbtnitem_1")
	protected WebElement cropYeastButton;

	@FindBy(xpath = "//a[contains(text(),'fermento descartado')]")
	protected WebElement spentYeastButton;

	@FindBy(id = "MenuTransferOptionsButton_tsbtnitem_0")
	protected WebElement transferOutYeastButton;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWYEAST']")
	protected WebElement yeastManagementButton;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWFILTER']")
	protected WebElement filtrationButton;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWBRIGHT']")
	protected WebElement brewBrightButton;

	@FindBy(xpath = "//a[contains(@title, 'Yeast Storage')]")
	protected WebElement yeastStorageButton;

	@FindBy(xpath = "//a[contains(@title,'Yeast Recovered Yeast')]")
	protected WebElement yeastRecoveryButton;

	@FindBy(xpath = "//a[contains(@title,'OD22')]")
	protected WebElement tankOD22;

	@FindBy(xpath = "//div[@class='tstile-text-medium'][normalize-space()='Filtração']")
	protected WebElement tankFiltration;

	@FindBy(xpath = "//div[@class='tstile-text-medium'][normalize-space()='Água Desaerada']")
	protected WebElement tankDAW;

	@FindBy(xpath = "//div[@class='tstile-text-medium'][contains(text(),'PVPP')]")
	protected WebElement tankPVPP;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/brewing/BREWUNITANK']")
	protected WebElement maturation;

	@FindBy(xpath = "//button[normalize-space()='Fechar']")
	protected WebElement closeButton;

	@FindBy(id = "ddlProductRecipe")
	protected WebElement selectRecipe;

	@FindBy(xpath = "//*[@id='ProductDetails']//li[1]/span[2]")
	protected WebElement batchSizeHL;

	@FindBy(xpath = "//*[@id='tsModalDialogInternalFooter']//button")
	protected WebElement warningCloseButton;

	@FindBy(xpath = "//div[normalize-space()='Tanque de Descarte']")
	protected WebElement yeastSpentTank;

	List<WebElement> equipments = new ArrayList<WebElement>();

	public void cleanWorkSpacePTs() throws Exception {
		// Go to Brewing
		brewingButton.click();

		// Go to TPs
		brewBrightButton.click();

		// Select a Random Tank in TPs
		selectRandomTPs();

		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeBatch();
		}

		// Opening a batch in filtration and a new batch in PTs
		cleanWorkSpaceFiltration();
	}

	/**
	 * This method cleans and creates a perfect workspace for the following test
	 * 
	 * @throws Exception
	 */
	public void cleanWorkSpaceFiltration() throws Exception {
		// Go to Filtration Tank
		filtrationButton.click();
		tankFiltration.click();

		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeBatch();
		}

		// Opening a new Skol Puro Malte batch
		newBatch.click();
		newBatchButton.click();
		newBatch("SK Filtração PRE - ( 38631 )", driver);

		// Starting the previous created Batch
		startYeastBatch();

		// Go to TPs
		brewBrightButton.click();

		// Select a Random Tank in TPs
		selectRandomTPs();

		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeBatch();
		}
	}

	/**
	 * This method cleans and creates a perfect workspace for the following test
	 * 
	 * @throws Exception
	 */
	public void cleanWorkSpaceYeast(String process) throws Exception {

		switch (process) {
		case "fermentation":
			// Go to Unitanks
			maturation.click();
			driver.findElement(By.xpath("//div[normalize-space()='OD" + getRandomNumberUniTank() + "']")).click();

			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			// Go to fermentation
			fermentationButton.click();

			// Selecting a random tank
			selectRandomTank("TilesMainSystems", "YeastFermentationTank");
			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			break;

		case "unitank":

			// Go to Unitanks
			maturation.click();

			// Selecting a random unitank
			selectRandomUniTankMaturation("TilesMainSystems", "YeastFermentationTank");
			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			// Go to Unitanks
			maturation.click();

			// Selecting a random unitank
			selectRandomUniTank("TilesMainSystems", "YeastFermentationTank");
			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			break;

		case "crop yeast tank":
			// Go to Unitanks
			maturation.click();
			driver.findElement(By.xpath("//div[normalize-space()='OD" + getRandomNumberUniTank() + "']")).click();

			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			// Opening a new MAINSTREAM R batch
			newBatch.click();
			newBatchButton.click();
			newBatch("CERVEJA MAINSTREAM FERMENTACAO R - ( 58597 )", driver);

			// Starting the previous created Batch
			startYeastBatch();

			// Go to fermentation
			fermentationButton.click();

			// Selecting a random tank
			selectRandomTank("TilesMainSystems", "YeastFermentationTank");
			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			break;

		case "yeast recovery tank":
			// Go to Unitanks
			maturation.click();
			driver.findElement(By.xpath("//div[normalize-space()='OD" + getRandomNumberUniTank() + "']")).click();

			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			// Opening a new MAINSTREAM R batch
			newBatch.click();
			newBatchButton.click();
			newBatch("CERVEJA MAINSTREAM MATURACAO R - ( 58618 )", driver);

			// Go to fermentation
			fermentationButton.click();

			// Selecting a random tank
			selectRandomTank("TilesMainSystems", "YeastFermentationTank");
			if (!isElementDisplayed(By.id("NoBatchNumber"))) {
				// Closing the running batch
				closeBatch();
			}

			break;

		default:
			break;
		}

		// Opening a new MAINSTREAM R batch
		newBatch.click();
		newBatchButton.click();
		newBatch("CERVEJA MAINSTREAM FERMENTACAO R - ( 58597 )", driver);

		// Starting the previous created Batch
		startYeastBatch();

		///////////////////////////////////////
		// Go to yeast management
		yeastManagementButton.click();
		yeastStorageButton.click();

		// Selecting a random tank
		selectRandomTank("YeastStorageTiles", "YeastCropTank");
		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeOrderButton.click();
			saveCancelButton.click();

		}
		///////////////////////////////////////
		// Go to Yeast Recovery tank OD22
		yeastRecoveryButton.click();
		tankOD22.click();

		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeBatch();
		}
	}

	/**
	 * This method selects a random tank for the equipment
	 * 
	 * @param id - The list of tanks id
	 */
	public void selectRandomTank(String id, String tank) {
		// Getting how many equipments there are
		equipments = driver.findElements(By.xpath("//*[@id='" + id + "']/a"));

		// Selecting a random equipment to open a Batch
		dataMap.put(tank, driver
				.findElement(By.xpath("//*[@id='" + id + "']/a[" + getRandomNumber() + "]//div[2]/div[1]")).getText());
		driver.findElement(By.xpath("//*[@id='" + id + "']/a[" + getRandomNumber() + "]")).click();
	}

	/**
	 * This method selects a random tank for the equipment to Maturation
	 * 
	 * @param id - The list of tanks id
	 */
	public void selectRandomUniTankMaturation(String id, String tank) {
		// Getting how many equipments there are
		equipments = driver.findElements(By.xpath("//*[@id='" + id + "']/a"));

		// Getting a random unitank number
		Random random = new Random();
		int randomNumberUniTank = random.ints(4, 10).findFirst().getAsInt();

		// Selecting a random equipment to open a Batch
		dataMap.put(tank,
				driver.findElement(By.xpath("//*[@id='" + id + "']/a[" + randomNumberUniTank + "]//div[2]/div[1]"))
						.getText());
		driver.findElement(By.xpath("//*[@id='" + id + "']/a[" + randomNumberUniTank + "]")).click();
	}

	/**
	 * This method selects a random tank for the equipment
	 * 
	 * @param id - The list of tanks id
	 */
	public void selectRandomUniTank(String id, String tank) {
		// Getting how many equipments there are
		equipments = driver.findElements(By.xpath("//*[@id='" + id + "']/a"));

		// Getting a random unitank number
		Random random = new Random();
		int randomNumber = random.nextInt(3);
		if (randomNumber == 0) {
			randomNumber = 1;
		}

		// Selecting a random equipment to open a Batch
		dataMap.put(tank,
				driver.findElement(By.xpath("//*[@id='" + id + "']/a[" + randomNumber + "]//div[2]/div[1]")).getText());
		driver.findElement(By.xpath("//*[@id='" + id + "']/a[" + randomNumber + "]")).click();
	}

	/**
	 * This method selects a random tank for the equipment to TPs
	 * 
	 */
	public void selectRandomTPs() {
		// Getting how many equipments there are
		equipments = driver.findElements(By.xpath("//div[@class = 'tstile-text-medium'][contains(text(),'TP')]"));

		// Getting a random unitank number
		Random random = new Random();
		int randomNumber = random.nextInt(equipments.size());

		if (randomNumber == 0) {
			randomNumber = 1;
		}

		// Selecting a random equipment to open a Batch
		WebElement equipment = driver.findElement(
				By.xpath("//div[@class = 'tstile-text-medium'][contains(text(),'TP" + randomNumber + "')]"));
		clickElementJS(driver, equipment);
	}

	/**
	 * This method creates a Yeast Sample in the Propagator and Transfer to a
	 * Fermentation Tank
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean createYeastSample(String process) throws Exception {
		cleanWorkSpaceYeast(process);
		// Go to Propagator Tank
		goToPropagator();
		propagatorTank.click();

		// Checking if there is a running Batch in the Propagator
		boolean isNoBatchNumber = isElementDisplayed(By.id("NoBatchNumber"));
		if (!isNoBatchNumber) {
			// Ending the Running Batch
			endOrder.click();
			clickElementJS(driver, saveCancelButton);
		}
		// Opening a New Yeast Batch
		newBatch.click();
		newBatchButton.click();
		newBatch("ITW263 Propagação - ( 26300 )", driver);

		// Starting the previous created Batch
		startYeastBatch();

		// Saving the Yeast Size to use later
		yeastCulturSize();

		// Saving Yeast Culture Code to compare Later
		driver.navigate().refresh();
		dataMap.put("YeastCultureCode", yeastCultureCode.getText());

		// Transfer the Batch
		transferButton.click();

		switch (process) {

		case "fermentation":
			transferYeast("(Fermentação) - Fermentation Tanks", "MAINSTREAM R");
			// Go to the Destination Tank
			fermentationButton.click();
			dataMap.put("Process", process);

			// Confirm Warning
			wait(3);
			if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
				warningButton.click();
			}
			break;

		case "unitank":
			transferYeast("(Maturação) - Uni-Tanks", "MAINSTREAM FERMENTACAO");
			// Go to the Destination Tank
			maturation.click();
			dataMap.put("Process", process);

			// Confirm Warning
			wait(3);
			if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
				warningButton.click();
			}
			break;

		case "crop yeast tank":
			transferYeast("(Fermentação) - Fermentation Tanks", "MAINSTREAM R");
			// Go to the Destination Tank
			fermentationButton.click();
			dataMap.put("Process", process);

			// Confirm Warning
			wait(3);
			if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
				warningButton.click();
			}
			break;

		case "yeast recovery tank":
			transferYeast("(Fermentação) - Fermentation Tanks", "MAINSTREAM R");
			// Go to the Destination Tank
			fermentationButton.click();

			// Confirm Warning
			wait(3);
			if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
				warningButton.click();
			}
			break;

		default:
			break;
		}

		// Go to the Destination Tank
		WebElement destinationTank = driver
				.findElement(By.xpath("//div[normalize-space()='" + dataMap.get("DestinationAreaNumber") + "']"));
		clickElementJS(driver, destinationTank);

		// Checking if the Yeast Culture Code is correct
		if (isElementDisplayed(By.xpath("//h2[normalize-space()='" + dataMap.get("YeastCultureCode") + "']"))) {
			log("The system successfully created a Yeast Culture Sample and Transfer");
		} else {
			logFail("The system was not create a Yeast Culture Sample and Transfer");
			return false;
		}
		return true;
	}

	/**
	 * This method transfer the yeast to a recovery tank
	 */
	public void transferYeastRecovery() {
		// Transfer the Yeast Recovered Yeast
		clickElementJS(driver, recoveryYeastButton);
		transferYeast("(Gestão de fermento) - Yeast Recovered Yeast", "OD22");

		// Checking if the transfer was successful
		checkTransferredYeast();
	}

	/**
	 * This method transfer the yeast from a recovery tank to a maturation tank
	 */
	public void transferYeastRecoveryToMaturation() {
		transferYeastRecovery();
		// Go to the recovery yeast tank
		yeastManagementButton.click();
		yeastRecoveryButton.click();
		tankOD22.click();

		// Transfer the Yeast Recovered Yeast
		clickElementJS(driver, transferRecoveryYeastButton);
		transferYeast("(Maturação) - Uni-Tanks", "MATURACAO R");

		// Checking if the transfer was successful
		checkTransferredYeast();
	}

	/**
	 * This method transfer the yeast to a storage tank
	 */
	public void cropYeast() {
		// Transfer the Crop Yeast
		clickElementJS(driver, cropYeastButton);
		transferYeast("(Gestão de fermento) - Yeast Storage", "Inativo");

		if (dataMap.get("Process").equalsIgnoreCase("crop yeast tank")) {
			// Go to Yeast Management
			yeastManagementButton.click();
			yeastStorageButton.click();

			// Go to the destination Yeast Storage Tank
			driver.findElement(By.xpath("//div[normalize-space()='" + dataMap.get("DestinationAreaNumber")
					+ "']//ancestor::div[contains(@class,'tstile-body')]")).click();
			dataMap.put("FermentationTub", dataMap.get("DestinationAreaNumber"));
		} else {
			// Checking if the transfer was successful
			checkTransferredYeast();
		}
	}

	/**
	 * This method transfer the yeast to a spent tank
	 */
	public void spentYeast() {
		// Transfer the Spent Yeast
		clickElementJS(driver, spentYeastButton);
		transferYeast("(Gestão de fermento) - Yeast Spent", "Tanque de Descarte");

		// Checking if the transfer was successful
		checkTransferredYeast();
	}

	public void spentAndCheckYeast() {
		spentYeast();
		// Go to the Spent Tank
		yeastManagement.click();
		clickElementJS(driver, yeastSpentTank);

		// Checking if the Yeast code is visible
		checkYeastCodeInSpent();
	}

	/**
	 * This method transfer out a yeast
	 */
	public void transferOut() {
		// Transfer the Crop Yeast

		clickElementJS(driver, transferOutYeastButton);

		switch (dataMap.get("Process")) {
		case "unitank":
			transferYeast("(Maturação) - Uni-Tanks", "Inativo");

			if (isElementDisplayed(By.cssSelector("#tsModalDialogInternal > div"))) {
				closeButton.click();

				Select recipe = new Select(selectRecipe);
				recipe.selectByVisibleText(
						"CERVEJA MAINSTREAM MATURACAO R (CERVEJA MAINSTREAM MATURACAO R [Uni-tank Template])");

				// Clicking to Transfer or Cancel the Batch
				clickElementJS(driver, transferCancelButton);
			}
			break;
		case "fermentation":
			transferYeast("(Maturação) - Uni-Tanks", "MAINSTREAM MATURACAO R");
			break;
		default:
			break;
		}

		// Checking if the transfer was successful
		checkTransferredYeast();
	}

	public void transferToPackaging() {
		// Transferring the batch
		transferFromPTsToPackLine();

		// Checking if the transfer was successful
		checkTransferredYeast();
	}

	/*
	 * This method transfer a batch from Pts to a Line in Packaging
	 */
	public void transferFromPTsToPackLine() {
		// Clicking the transfer Out Button
		transferButton.click();

		// Inputing the Batch Size
		quantity.clear();
		quantity.sendKeys(batchSize());

		// Selecting the Process Area as the where the workspace method opened a order
		processAreaCombobox.click();
		WebElement destinationArea = driver.findElement(
				By.xpath("//*[@id='ddlProcessArea']/option[contains(text(),'" + dataMap.get("Line") + "')]"));

		// Saving the Destination Area
		String destinationAreaNumber = destinationArea.getText();
		destinationAreaNumber = StringUtils.substringAfter(destinationAreaNumber, "- ");
		dataMap.put("DestinationAreaNumber", destinationAreaNumber);

		// Clicking the Destination Area
		destinationArea.click();

		// Clicking to Transfer or Cancel the Batch
		clickElementJS(driver, transferCancelButton);
	}

	/**
	 * This method transfer out from filtration tank to a TPs
	 * 
	 * @throws Exception
	 */
	public void transferToTPs() throws Exception {
		// Go to Filtration Tank
		filtrationButton.click();
		tankFiltration.click();

		// Clicking the transfer Out Button
		transferButton.click();
		wait(2);

		// Checking if the warning is displayed
		checkingWarningDiplayed();

		// Transfer to TPs
		transferTPs();

	}

	/**
	 * This method checks if the warning is displayed to the user. If so it closes
	 * the running batch and opens a new one and transfers.
	 * 
	 * @throws Exception
	 */
	public void checkingWarningDiplayed() throws Exception {
		// Searching if the warning is displayed to the user
		if (isElementDisplayed(By.xpath("//div[@class='modal-content']"))) {
			// Closing the warning
			warningCloseButton.click();
			// Closing the running batch
			closeBatch();
			// Opening a New Yeast Batch
			newBatch.click();
			newBatchButton.click();
			newBatch("SK Filtração PRE - ( 38631 )", driver);
			// Clicking the transfer Out Button
			transferButton.click();
		}
	}

	public void transferTPs() {
		// Inputing the Batch Size
		quantity.clear();
		quantity.sendKeys(batchSize());

		// Selecting the Process Area
		Select processArea = new Select(processAreaCombobox);
		processArea.selectByVisibleText("(Adega de Pressão) - Bright Beer");

		transferLocation.click();
		WebElement destinationArea = driver
				.findElement(By.xpath("//*[@id='ddlTransferLocations']/option[contains(text(),'Inativo')]"));

		// Saving the Destination Area
		String destinationAreaNumber = destinationArea.getText();
		destinationAreaNumber = StringUtils.substringBefore(destinationAreaNumber, " (");
		dataMap.put("DestinationAreaNumber", destinationAreaNumber);

		destinationArea.click();

		// Clicking to Transfer or Cancel the Batch
		clickElementJS(driver, transferCancelButton);

		// Checking if the transfer was successfully finished
		checkTransferredYeast();
	}

	/**
	 * This method transfer Yeast Pitching to Fermentation and Unitank
	 */
	public void transferYeastPitchingToFermentationAndUnitank() {
		// Transfer Yeast Pitching
		clickElementJS(driver, transferOutYeastButton);

		// Transfer to Fermentation Tanks
		transferYeast("(Fermentação) - Fermentation Tanks", "MAINSTREAM R");

		// Confirm Warning
		wait(3);
		if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
			warningButton.click();
		}

		// Checking if the transfer was successful
		checkTransferredYeast();

		// Transfer Yeast Pitching
		clickElementJS(driver, transferOutYeastButton);

		// Transfer to Fermentation Tanks
		transferYeast("(Maturação) - Uni-Tanks", "MAINSTREAM R");

		// Confirm Warning
		wait(3);
		if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
			warningButton.click();
		}

		// Checking if the transfer was successful
		checkTransferredYeast();
	}

	/**
	 * This method transfer a Maturation Batch and a MainsStream R Batch from
	 * maturation to filtration
	 * 
	 * @throws Exception
	 */
	public void transferFromMaturationToFiltration() throws Exception {
		// Opening a Maturation Batch in Maturation
		openBatchtesToFiltration("CERVEJA SKOL MATURACAO HG - ( 38551 )");
		// Transferring the previous created batch to filtration
		transferToFiltration("Maturation");
		// Opening a Mainstream R Batch in Maturation
		openBatchtesToFiltration("CERVEJA MAINSTREAM MATURACAO R - ( 58618 )");
		// Transferring the previous created batch to filtration
		transferToFiltration("Maturation");
	}

	/**
	 * This method transfer a Batch from PVPP or DAW to Filtration
	 * 
	 * @throws Exception
	 */
	public void transferFromProcessToFiltration(String process) throws Exception {
		// Opening a SK Filtration Batch in PVPP
		openBatchToFiltration(process);
		// Transferring the previous created batch to filtration
		transferToFiltration("PVVP or DAW");
	}

	public void transferTankToTank() {
		transferFromTankToTank();
	}

	/**
	 * This method transfer a batch from PTs to Filtration again
	 * 
	 * @throws Exception
	 */
	public void transferToRefiltration() throws Exception {
		// Cleaning the workspace to the test
		cleanWorkSpacePTs();
		// Opening a Sk batch in PTs
		openBatchPTs();
		// Transfer from PTs to Refiltering
		transferToFiltration("PTs");
	}

	/**
	 * This method opens a new batch and starts it
	 * 
	 * @throws Exception
	 */
	public void openBatchPTs() throws Exception {
		// Opening a New PVPP Batch
		newBatch.click();
		newBatchButton.click();
		newBatch("SK Filtração PRE - ( 38631 )", driver);

		// Starting the previous created Batch
		startYeastBatch();
	}

	/**
	 * This method open a batch and starts it depending on the Process
	 * 
	 * @param process - Process can be DAW or PVPP
	 * @throws Exception
	 */
	public void openBatchToFiltration(String process) throws Exception {
		String product = null;
		switch (process) {
		case "PVPP":
			// Go to the PVPP tank
			filtrationButton.click();
			tankPVPP.click();
			// Setting the product
			product = "SK Filtração PRE - ( 38631 )";
			break;

		case "DAW":
			// Go to the PVPP tank
			filtrationButton.click();
			tankDAW.click();
			// Setting the product
			product = "Água Desaerada - ( 26305 )";
			break;
		default:
			break;
		}

		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeBatch();
		}
		// Opening a New PVPP Batch
		newBatch.click();
		newBatchButton.click();
		newBatch(product, driver);

		// Starting the previous created Batch
		startYeastBatch();
	}

	/**
	 * This method creates a brand batch and a mainstream R batch in the maturation
	 * to be transfer out to filtration
	 * 
	 * @throws Exception
	 */
	public void openBatchtesToFiltration(String product) throws Exception {
		// Opening a Batch in the Filtration Area for the test workspace
		if (product.equalsIgnoreCase("CERVEJA SKOL MATURACAO HG - ( 38551 )")) {
			cleanWorkSpaceFiltration();
		}
		dataMap.put("Product", product);

		// Go to Unitanks
		maturation.click();
		driver.findElement(By.xpath("//div[normalize-space()='OD" + getRandomNumberUniTank() + "']")).click();

		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Closing the running batch
			closeBatch();
		}
		// Opening a New Yeast Batch
		newBatch.click();
		newBatchButton.click();
		newBatch(product, driver);

		// Starting the previous created Batch
		startYeastBatch();

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

		// Confirm Cancel
		wait(3);
		if (isElementDisplayed(By.id("contentPage_FormValidation_Warning_Button"))) {
			warningButton.click();
		}

		driver.navigate().refresh();
		// Checking if there is any active Batch
		if (isElementDisplayed(By.id("TileHistoricalMode"))) {
			log("The system sucessfully Cancelled a Batch");
		} else {
			logFail("The system was not able to Cancel a Batch");
			return false;
		}
		return true;
	}

	/**
	 * This method generate a random number between the range 13 to 15
	 * 
	 * @return - Random number in the range of 13 to 15
	 */
	public int getRandomNumberUniTank() {
		Random random = new Random();
		int randomNumberUniTank = random.ints(13, 16).findFirst().getAsInt();
		dataMap.put("RandomNumberUniTank", Integer.toString(randomNumberUniTank));
		return randomNumberUniTank;
	}

	/**
	 * This method generate a random number in the equipment range
	 * 
	 * @return - Random number in the equipment
	 */
	public int getRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(equipments.size() - 1);
		if (randomNumber == 0 || randomNumber == -1) {
			randomNumber = 1;
		}
		return randomNumber;
	}

	/**
	 * This method check if the last destination Location s displayed as the last
	 * location in the WIP Movements Section
	 * 
	 * @return - if the transfer was successful or not
	 */
	public boolean checkTransferredYeast() {
		wait(1);
		if (!isElementDisplayed(
				By.xpath("//tr[1]/td[normalize-space()='" + dataMap.get("DestinationAreaNumber") + "']"))) {
			logFail("The system was not able to Transfer the Yeast");
			return false;
		}
		log("The system successfully transferred Out the Yeast");

		return true;
	}

	/**
	 * This method does all types of Transfer according to the given Process Area
	 * and Destination Location
	 * 
	 * @param processAreaName      - One possible Process Area for the transfer type
	 * @param transferLocationName - One possible Destination for the transfer type
	 */
	public void transferYeast(String processAreaName, String transferLocationName) {
		// Inputing the Yeast Culture Size
		quantity.clear();
		quantity.sendKeys(dataMap.get("YeastCultureSize"));

		// Selecting the Process Area
		Select processArea = new Select(processAreaCombobox);
		processArea.selectByVisibleText(processAreaName);

		// Selecting the Destination Area
		transferLocation.click();
		WebElement destinationArea = driver.findElement(
				By.xpath("//*[@id='ddlTransferLocations']/option[contains(text(),'" + transferLocationName + "')]"));

		// Saving the Destination Area
		String destinationAreaNumber = destinationArea.getText();
		destinationAreaNumber = StringUtils.substringBefore(destinationAreaNumber, " (");
		dataMap.put("DestinationAreaNumber", destinationAreaNumber);

		// Clicking the Destination Area
		destinationArea.click();

		// Clicking to Transfer or Cancel the Batch
		clickElementJS(driver, transferCancelButton);

		wait(1);
		if (isElementDisplayed(By.xpath("//label[contains(text(),'erro')]"))) {
			closeButton.click();
			Select recipesCombobox = new Select(selectRecipe);
			recipesCombobox.selectByVisibleText(
					"CERVEJA MAINSTREAM MATURACAO R (CERVEJA MAINSTREAM MATURACAO R [Uni-tank Template])");
			// Clicking to Transfer or Cancel the Batch
			clickElementJS(driver, transferCancelButton);
		}
	}

	public boolean checkYeastCodeInSpent() {
		if (isElementDisplayed(By.id("YeastCultureCode"))) {
			logFail("The Yeast Culture COde is being shown in the spent yeast screen");
			return false;
		}
		return true;
	}

	/**
	 * This method does the transfer to the filtration Area
	 * 
	 * @param origin - The origin from the transfer
	 */
	public void transferToFiltration(String origin) {
		if (origin.equalsIgnoreCase("PTs")) {
			clickElementJS(driver, refilterButton);
		} else {
			clickElementJS(driver, transferButton);
		}
		// Inputing the Batch Size
		quantity.clear();
		if (dataMap.get("Product").equalsIgnoreCase("CERVEJA SKOL MATURACAO HG")) {
			quantity.sendKeys(batchSize());
		} else {
			quantity.sendKeys("100");
		}

		// Selecting the Process Area
		Select processArea = new Select(processAreaCombobox);
		processArea.selectByVisibleText("(Filtração) - Filter Lines");

		dataMap.put("DestinationAreaNumber", "Filtração");

		// Clicking to Transfer or Cancel the Batch
		clickElementJS(driver, transferCancelButton);

		// Checking if the transfer was successfully finished
		checkTransferredYeast();
	}

	public void transferFromTankToTank() {
		clickElementJS(driver, tankToTankButton);

		// Inputing the Batch Size
		quantity.clear();
		quantity.sendKeys(batchSize());

		// Selecting the Process Area
		Select processArea = new Select(processAreaCombobox);
		processArea.selectByVisibleText("(Adega de Pressão) - Bright Beer");

		// Selecting the Location
		driver.findElement(By.id("ddlTransferLocations")).click();
		WebElement destinationArea = driver
				.findElement(By.xpath("//*[@id='ddlTransferLocations']/option[contains(text(),'Inativo')]"));

		// Saving the Destination Area
		String destinationAreaNumber = destinationArea.getText();
		destinationAreaNumber = StringUtils.substringBefore(destinationAreaNumber, " (");
		dataMap.put("DestinationAreaNumber", destinationAreaNumber);

		destinationArea.click();

		// Clicking to Transfer or Cancel the Batch
		clickElementJS(driver, transferCancelButton);

		// Checking if the transfer was successfully finished
		checkTransferredYeast();
	}

	/**
	 * This method gets the batch size without the weight measures
	 * 
	 * @return - The Batch Size without the weight measures
	 */
	public String batchSize() {
		String batchSize = batchSizeHL.getText();
		batchSize = StringUtils.substringBefore(batchSize, " HL");
		return batchSize;
	}

	/**
	 * This method gets the Yeast Culture size in the Transfer Page
	 * 
	 */
	public void yeastCulturSize() {
		String yeastCultureSize = yeastCultureSizeKG.getText();
		yeastCultureSize = StringUtils.substringBefore(yeastCultureSize, " kg");
		dataMap.put("YeastCultureSize", yeastCultureSize);
	}

	/**
	 * This method goes to Propagator
	 */
	public void goToPropagator() {
		yeastManagement.click();
		yeastPropagation.click();
		propagator.click();
	}

	/**
	 * This method starts the previous created batch and verifies if the Batch
	 * Number is displayed in the running batch Screen
	 * 
	 * @return
	 */
	public boolean startYeastBatch() {
		// Starting a Batch
		WebElement startYeastBatch = driver.findElement(
				By.xpath("//a[normalize-space()='" + dataMap.get("BatchNumber") + "']//ancestor::tr//td[11]/a"));
		clickElementJS(driver, startYeastBatch);
		clickElementJS(driver, startButton);

		// Waiting the Batch to Start
		waitForElement(title, 8);

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