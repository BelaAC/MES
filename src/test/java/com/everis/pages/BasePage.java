package com.everis.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.sikuli.hotkey.Keys;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Finder;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.everis.util.TestRule;
import com.everis.util.Utils;

import io.github.marcoslimaqa.sikulifactory.SikuliElement;

public class BasePage {
	public static List<String> productOptionText = new ArrayList<String>();
	public static String dashboardValue;
	public static String outOfSoecDashboardValue;
	public static Map<String, String> dataMap = new HashMap<>();
	public static JavascriptExecutor javaScript;
	protected WebDriver driver = TestRule.getDriver();
	protected ExtentTest extentTest = TestRule.getExtentTest();
	protected ExtentReports extentReport = TestRule.getExtentReports();
	protected Screen sikuli = TestRule.getSikuli();
	protected App sikuliApp = TestRule.getSikuliApp();
	protected boolean isSikuliAutomation = "sikuli".equals(TestRule.getActiveAutomation());
	public final Logger logger = Logger.getLogger(BasePage.class);
	public static String downloadPath = TestRule.downloadPath;

	@FindBy(id = "CmbProduct")
	protected static WebElement productsCombobox;

	@FindBy(id = "TxtJobName")
	protected static WebElement batchNumber;

	@FindBy(id = "BtnSaveCancel_Save")
	protected static WebElement saveButton;

	/**
	 * This method creates a new Batch using the given parameter
	 * 
	 * @param product - Selected Product in the Product Combobox
	 * @throws Exception
	 */
	public static void newBatch(String product, WebDriver driver) throws Exception {
		// Selecting a Product by xpath. Do not change this to a select because the page
		// refresh it self
		productsCombobox.click();
		driver.findElement(By.xpath("//*[@id='CmbProduct']/option[contains(text(),'" + product + "')]")).click();

		// Getting a substring of the product to compare when the Batch is edited
		product = StringUtils.substringBefore(product, " -");

		// Getting Selected Product and Batch Number
		dataMap.put("Product", product);
		dataMap.put("BatchNumber", batchNumber.getAttribute("value"));

		// Saving
		saveButton.click();
	}

	public static void waitForPageToLoad(WebDriver driver) throws Exception {
		JavascriptExecutor jsExecutor;
		jsExecutor = (JavascriptExecutor) driver;
		try {
			if (jsExecutor.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
				return;
			} else {
				for (int i = 0; i <= 60; i++) {
					try {
						Thread.sleep(1000);
						if (jsExecutor.executeScript("return document.readyState").toString()
								.equalsIgnoreCase("complete")) {
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (JavascriptException e) {
			e.printStackTrace();
		}

	}

	public static List<String> correctEventsList() {
		List<String> correctEventExternalCauses = new ArrayList<String>();
		correctEventExternalCauses.add("Log??stica");
		correctEventExternalCauses.add("Processo Cerveja");
		correctEventExternalCauses.add("Qualidade");
		correctEventExternalCauses.add("Suprimentos");
		correctEventExternalCauses.add("Utilidades");
		Collections.sort(correctEventExternalCauses);
		return correctEventExternalCauses;
	}

	public static List<String> correctDefinitionOptionsDowntimeList() {
		List<String> correctEventDowntimePlanned = new ArrayList<String>();
		correctEventDowntimePlanned.add("Setup");
		correctEventDowntimePlanned.add("Manuten????o Programada");
		correctEventDowntimePlanned.add("M??dulo de Manuten????o");
		correctEventDowntimePlanned.add("Setup de Embalagem Secund??ria");
		correctEventDowntimePlanned.add("Setup");
		correctEventDowntimePlanned.add("CIP 1 (Enx??g??e)");
		correctEventDowntimePlanned.add("CIP 2 (Alcalino)");
		correctEventDowntimePlanned.add("CIP-3 (??cido)");
		correctEventDowntimePlanned.add("Assepsia Externa (Espuma)");
		correctEventDowntimePlanned.add("Scrubbing");
		correctEventDowntimePlanned.add("Invent??rio");
		correctEventDowntimePlanned.add("In??cio");
		correctEventDowntimePlanned.add("Final");
		correctEventDowntimePlanned.add("Corte de Produ????o");
		correctEventDowntimePlanned.add("Troca de Molde");
		correctEventDowntimePlanned.add("Setup de Emb. Diametro");
		correctEventDowntimePlanned.add("Setup de Emb. Altura");
		correctEventDowntimePlanned.add("Setup de Emb. Diametro e Altura");
		Collections.sort(correctEventDowntimePlanned);
		return correctEventDowntimePlanned;
	}

	public void clickButton(String button) throws Exception {
		driver.findElement(By.xpath("//a[normalize-space()='" + button + "']")).click();
		log("Clicked the button: " + button);
	}

	/**
	 * Scroll a WebElement into view using Javascript
	 * 
	 * @param driver  - Browser driver
	 * @param element - Target WebElement
	 * @author // TODO
	 */
	public static void scrollToElementJS(WebDriver driver, WebElement element) {
		javaScript = (JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Perform a scroll-and-click action using Javascript
	 * 
	 * @param driver  - Browser driver
	 * @param element - Target WebElement
	 * @author // TODO
	 */
	public static void clickElementJS(WebDriver driver, WebElement element) {
		scrollToElementJS(driver, element);
		javaScript = (JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].click();", element);
	}

	/**
	 * Perform a random select in a Dropdown List by xpath
	 * 
	 * @param driver            - Browser driver
	 * @param dropDownListXpath - Target WebElement
	 * @param dataMapField      - DataMap String
	 * @throws Exception
	 */
	public static void selectRandomElementInDropdownList(WebDriver driver, String dropDownListXpath,
			String dataMapField) throws Exception {
		Select list = new Select(driver.findElement(By.xpath(dropDownListXpath)));
		List<WebElement> itemsInDropdown = list.getOptions();
		int size = itemsInDropdown.size();
		Random random = new Random();
		int randomNumber = random.nextInt(size);

		if (randomNumber <= 1) {
			randomNumber = 2;
		}

		itemsInDropdown.get(randomNumber).click();
		String selected = list.getFirstSelectedOption().getText().trim();
		dataMap.put(dataMapField, selected);
	}

	public boolean verificarQuantidadeDeImagensExibidas(String imagem, int quantImagens) throws FindFailed {
		posicionarMouseCentroTela();

		List<Match> totalImagens = new ArrayList<Match>();
		Iterator<Match> imagens = sikuli.findAll(imagem);

		for (Iterator<Match> img = imagens; img.hasNext();) {
			Match image = img.next();
			image.highlight();
			waitMilliseconds(100);
			image.highlight();
			totalImagens.add(image);
		}

		int total = totalImagens.size();

		if (total == quantImagens) {
			log("Total de [" + quantImagens + "] imagens como era esperado.");
			return true;
		}

		logFail("Total de imagens [" + total + "] nao era esperado. O esperado era [" + quantImagens + "]. ");
		return false;
	}

	public int retornarQuantidadeDeImagensExibidas(String imagem) throws FindFailed {
		posicionarMouseCentroTela();

		List<Match> totalImagens = new ArrayList<Match>();
		Iterator<Match> imagens;
		try {
			imagens = sikuli.findAll(imagem);
		} catch (FindFailed e) {
			imagens = null;
		}
		if (imagens != null) {
			for (Iterator<Match> img = imagens; img.hasNext();) {
				Match image = img.next();
				image.highlight();
				waitMilliseconds(100);
				image.highlight();
				totalImagens.add(image);
			}
		}
		int total = totalImagens.size();
		return total;
	}

	protected void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.error("Erro ao executar wait(int seconds)", e);
		}
	}

	protected void waitMilliseconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			logger.error("Erro ao executar wait(int milliseconds)", e);
		}
	}

	protected WebElement waitElement(By by, int timeOutInSeconds) {
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	protected boolean waitForElement(WebElement element, int timeOutInSeconds) {
		WebElement webElement = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(TimeoutException.class);
			webElement = wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException e) {
			return false;
		}
		return webElement != null;
	}

	protected boolean waitForElement(By byElement, int timeOutInSeconds) {
		WebElement webElement = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(TimeoutException.class);
			webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException e) {
			return false;
		}
		return webElement != null;
	}

	protected WebElement waitElement(WebElement webElement, int timeOutInSeconds) {
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element;
	}

	protected List<WebElement> waitElements(By by, int timeOutInSeconds) {
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		return element;
	}

	protected boolean waitNotPresent(By by, int timeOutInSeconds) {
		WebDriver driver = TestRule.getDriver();

		waitMilliseconds(500);
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		boolean isElementInvisible = false;
		try {
			isElementInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			return false;
		}
		return isElementInvisible;
	}

	public void fillInputAndWaitUntilElementHasValue(WebElement element, String text) {
		boolean hasValue = false;
		int maxRetry = 0;
		while (!hasValue) {
			if (maxRetry >= 5) {
				break;
			}
			element.clear();
			element.sendKeys(text);
			hasValue = waitUntilElementHasValue(element, text);
			maxRetry++;
		}
	}

	protected boolean waitUntilElementHasValue(WebElement element, String text) {
		boolean isElementhasText = false;
		try {
			waitMilliseconds(500);
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(1, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
			isElementhasText = wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
			return isElementhasText;
		} catch (Exception e) {
			return isElementhasText;
		}
	}

	protected void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	protected boolean isElementDisplayed(By by) {
		boolean isElementPresent = false;
		boolean isElementDisplayed = false;
		isElementPresent = !driver.findElements(by).isEmpty();
		if (isElementPresent) {
			isElementDisplayed = driver.findElement(by).isDisplayed();
		}
		return isElementPresent && isElementDisplayed;
	}

	protected void aguardarLoading() {
		try {
			waitElement(By.id("loadingScreen"), 3);
			waitElement(By.id("loadingNovosExtratos"), 2);
		} catch (Exception e) {
		}
		waitNotPresent(By.id("loadingScreen"), 120);
		waitNotPresent(By.id("loadingNovosExtratos"), 120);
	}

	private String saveScreenshotInRelatoriosPath() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int milliseconds = calendar.get(Calendar.MILLISECOND);
		String datahora = "" + day + month + year + "_" + hours + minutes + seconds + milliseconds;
		String screenShotName = datahora + ".png";
		File scrFile = null;
		try {
			if (isSikuliAutomation) {
				scrFile = new File("target/report/html/img/" + screenShotName);
				try {
					ImageIO.write(sikuli.capture(App.focusedWindow()).getImage(), "png", scrFile);
				} catch (Exception e) {
					ImageIO.write(sikuli.capture().getImage(), "png", scrFile);
					logger.debug("Erro ao obter screenshot do app, possivelmente o app '" + sikuliApp.getName()
							+ "' nao esta em execucao." + "Obtido screenshot da tela inteira.");
				}
			} else {
				scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("target/report/html/img/" + screenShotName));
			}
		} catch (IOException e) {
			logger.error("Erro ao salvar screenshot.", e);
		}
		return screenShotName;
	}

	protected void log(String log) {
		String screenShotName = saveScreenshotInRelatoriosPath();
		try {
			extentTest.pass(log, MediaEntityBuilder.createScreenCaptureFromPath("img/" + screenShotName).build());
		} catch (IOException e) {
			logger.error("Erro ao executar logPrint()", e);
		}
	}

	public void logPrintFail(String log) {
		String screenShotName = saveScreenshotInRelatoriosPath();
		try {
			extentTest.fail(log, MediaEntityBuilder.createScreenCaptureFromPath("img/" + screenShotName).build());
		} catch (IOException e) {
			logger.error("Erro ao executar logPrintFail()", e);
		}
	}

	protected void logInfo(String log) {
		extentTest.info(log);
	}

	protected void logSkip(String log) {
		extentTest.skip(log);
	}

	protected void logFail(String log) {
		extentTest.fail(log);
	}

	protected void logError(String log) {
		extentTest.error(log);
	}

	protected void logPass(String log) {
		extentTest.pass(log);
	}

	protected ExtentTest createChildStart(String strNomeTeste) {
		ExtentTest parentTest = TestRule.getExtentTest();
		ExtentTest child = parentTest.createNode(strNomeTeste);
		return child;
	}

	protected void childLogFail(ExtentTest child, String log) {
		child.fail(log);
	}

	protected void childLogPass(ExtentTest child, String log) {
		child.pass(log);
	}

	protected void childLogInfo(ExtentTest child, String log) {
		child.info(log);
	}

	protected boolean imageExists(String imageFile, float similarity0to100) {
		Match image = sikuli.exists(new Pattern(imageFile).similar(similarity0to100 / 100));
		boolean imageExists = image != null;
		return imageExists;
	}

	protected boolean imageExists(String imageFile, float similarity0to100, double timeOutInSeconds) {
		Match image = sikuli.exists(new Pattern(imageFile).similar(similarity0to100 / 100), timeOutInSeconds);
		boolean imageExists = image != null;
		return imageExists;
	}

	protected boolean assertImageExists(String imageFile, float similarity0to100) {
		try {
			sikuli.wait(imageFile, 15);
		} catch (FindFailed e1) {
			logInfo("Imagem nao foi exibida" + e1);
		}
		try {
			FileUtils.copyFile(new File(ImagePath.find(imageFile).getPath()),
					new File("target/report/html/img/" + imageFile));
			extentTest.info("Imagem esperada: " + imageFile,
					MediaEntityBuilder.createScreenCaptureFromPath("img/" + imageFile).build());
		} catch (IOException e) {
			logger.error("Erro ao executar o metodo assertImageExists()", e);
		}

		Match image = sikuli.exists(new Pattern(imageFile).similar(similarity0to100 / 100));
		boolean imageExists = image != null;

		if (imageExists) {
			image.highlight();
			waitMilliseconds(30);
			log("Imagem encontrada com " + new DecimalFormat("#.##").format((image.getScore() * 100))
					+ " % de similaridade");
			image.highlight();
		} else {
			log("A imagem " + imageFile + " nao foi encontrada com a similaridade de " + similarity0to100 + " %");
		}
		return imageExists;
	}

	protected int click(String imageFile) {
		try {
			return sikuli.click(imageFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int click(String imageFile, float similarity0to100) {
		try {
			return sikuli.click(new Pattern(imageFile).similar(similarity0to100 / 100));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int click(String imageFile, float similarity0to100, int x, int y) {
		try {
			return sikuli.click(new Pattern(imageFile).similar(similarity0to100 / 100).targetOffset(x, y));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected int doubleClick(String imageFile) {
		try {
			return sikuli.doubleClick(imageFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String getText(String imageFile, float similarity0to100) {
		clearClipboard();
		click(imageFile, similarity0to100);
		sikuli.keyDown(Key.CTRL);
		sikuli.keyDown(Key.INSERT);
		sikuli.keyUp();
		return getClipboard();
	}

	protected String getText() {
		clearClipboard();
		sikuli.type("c", Key.CTRL);
		return getClipboard();
	}

	protected String copiarValoresLinhaDoRegistro() {
		clearClipboard();
		sikuli.keyDown(Key.CTRL);
		sikuli.keyDown(Key.ALT);
		sikuli.keyDown(Key.SHIFT);
		sikuli.type("c");
		sikuli.keyUp();
		return getClipboard();
	}

	protected String getText(String imageFile, float similarity0to100, int x, int y) {
		clearClipboard();
		click(imageFile, similarity0to100, x, y);
		type("a", Key.CTRL);
		type("c", Key.CTRL);
		return getClipboard();
	}

	public void clearClipboard() {
		StringSelection selection = new StringSelection("");
		Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard2.setContents(selection, selection);
	}

	public String getClipboard() {
		String clipboardText = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			waitMilliseconds(500);
			clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			logger.error("Erro ao obter texto da area de transferencia", e);
		}
		return clipboardText;
	}

	protected Match wait(String imagem, int tempoMaximoSegundos) {
		try {
			return sikuli.wait(imagem, tempoMaximoSegundos);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	protected Match wait(SikuliElement imagem, int tempoMaximoSegundos) {
		try {
			return sikuli.wait(imagem, tempoMaximoSegundos);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	protected Match wait(String imagem, int tempoMaximoSegundos, float similarity0to100) {
		try {
			return sikuli.wait(new Pattern(imagem).similar(similarity0to100 / 100), tempoMaximoSegundos);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	protected String waitRegionChange(Region region, int timeoutInSeconds) {
		long timeoutExpiredMs = System.currentTimeMillis() + timeoutInSeconds * 1000;
		String eventsName = region.onChange();
		region.observe(timeoutInSeconds);
		region.stopObserver();
		if (System.currentTimeMillis() >= timeoutExpiredMs) {
			throw new RuntimeException("Timeout waitRegionChange " + timeoutInSeconds + " seconds: " + region);
		}
		return eventsName;
	}

	public void pressionarAtalho(String atalho) {
		String[] teclas = atalho.replaceAll("ctrl", Key.CTRL).replaceAll("shift", Key.SHIFT).replaceAll("alt", Key.ALT)
				.replace("space", Key.SPACE).split(" ");
		int quantTeclas = teclas.length;
		if (teclas[0].matches(Key.CTRL + "|" + Key.SHIFT + "|" + Key.ALT)) {
			if (quantTeclas == 2) {
				sikuli.type(teclas[1], teclas[0]);
			} else if (quantTeclas == 3) {
				sikuli.type(teclas[2], teclas[0] + teclas[1]);
			} else if (quantTeclas == 4) {
				sikuli.type(teclas[3], teclas[0] + teclas[1] + teclas[2]);
			}
		} else if (quantTeclas == 1) {
			sikuli.type(atalho);
		}
		log("Pressionou o atalho: " + atalho);
	}

	protected App appFocus(String appName) {
		sikuliApp = App.focus(appName);
		TestRule.setSikuliApp(sikuliApp);
		return sikuliApp;
	}

	protected void multiType(String arg, int count) {
		for (int i = 0; i < count; i++) {
			type(arg);
		}
	}

	protected int type(String texto) {
		return sikuli.type(texto);
	}

	protected int type(String text, String modifiers) {
		return sikuli.type(text, modifiers);
	}

	protected int paste(String texto) {
		return sikuli.paste(texto);
	}

	protected void multiClick(int clicks, String imageFile) {
		try {
			for (int i = 0; i < clicks; i++) {
				sikuli.click(imageFile);
				waitMilliseconds(500);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * OBS: Quando o titulo da janela a ser aguardada possuir acentuacao ou
	 * caracteres especiais, utilize no paramentro windowTitle apenas parte do
	 * titulo da janela, sem informar o caractere especial. Exemplo: No caso do
	 * titulo se chamar "M??dulo de Pesquisa", utilize apenas "de Pesquisa"
	 * 
	 * @param windowTitle
	 */
	public void waitWindow(String windowTitle, int timeoutInSeconds) {
		boolean isWindowOpened = false;
		int pollingEvery = 500;
		double timeout = (double) timeoutInSeconds * 1000.0;
		double elapsedTime = 0.0;

		while (elapsedTime < timeout) {
			appFocus(windowTitle);
			isWindowOpened = sikuliApp.getPID() > 0 && sikuliApp.toString().contains(windowTitle);
			if (!isWindowOpened) {
				waitMilliseconds(pollingEvery);
				elapsedTime += (double) pollingEvery;
			} else {
				break;
			}
		}

		if (!isWindowOpened) {
			logger.error("The window containing the text '" + windowTitle + "' in the title was not opened.");
			throw new RuntimeException(
					"The window containing the text '" + windowTitle + "' in the title was not opened.");
		}
	}

	public boolean exibiuJanela(String windowTitle, int timeoutInSeconds) {
		boolean isWindowOpened = false;
		int pollingEvery = 500;
		double timeout = (double) timeoutInSeconds * 1000.0;
		double elapsedTime = 0.0;

		while (elapsedTime < timeout) {
			appFocus(windowTitle);
			isWindowOpened = sikuliApp.getPID() > 0 && sikuliApp.toString().contains(windowTitle);
			if (!isWindowOpened) {
				waitMilliseconds(pollingEvery);
				elapsedTime += (double) pollingEvery;
			} else {
				break;
			}
		}

		if (!isWindowOpened) {
			logPrintFail("A janela" + windowTitle + "nao foi exibida");
			logger.error("The window containing the text '" + windowTitle + "' in the title was not opened.");
			throw new RuntimeException(
					"The window containing the text '" + windowTitle + "' in the title was not opened.");
		}

		return isWindowOpened;
	}

	public void closeWindow(String windowTitle) {
		waitWindow(windowTitle, 5);
		App.close(windowTitle);
	}

	/**
	 * No mac o App.focus nao funciona bem com parte o title do aplicativo. Fuciona
	 * apenas com o nome do aplicativo. como alternativa, este metodo recebe uma
	 * imagem referente a tela que se esteja aguardar.
	 * 
	 * @param windowTitle
	 * @param anyExpectedSikuliElementInWindow
	 * @param timeoutInSeconds
	 */
	public void waitWindow(String windowTitle, SikuliElement anyExpectedSikuliElementInWindow, int timeoutInSeconds) {
		anyExpectedSikuliElementInWindow.wait(timeoutInSeconds);
		if (Utils.isWindows()) {
			waitWindow(windowTitle, timeoutInSeconds);
		}
	}

	protected List<List<String>> htmlTableToDataTable(WebElement htmlTable) {
		List<List<String>> dataTable = new ArrayList<List<String>>();
		List<String> linhaDataTable = new ArrayList<String>();
		List<WebElement> tabelaLinhas = htmlTable.findElements(By.cssSelector("tr"));
		List<WebElement> colunasDaLinha;

		for (WebElement linha : tabelaLinhas) {
			colunasDaLinha = linha.findElements(By.cssSelector("td,th"));
			for (WebElement coluna : colunasDaLinha) {
				linhaDataTable.add(coluna.getText());
			}
			dataTable.add(linhaDataTable);
			linhaDataTable = new ArrayList<String>();
		}
		return dataTable;
	}

	public void removeColumnFromDataTable(List<List<String>> dataTable, int column) {
		for (int i = 0; i < dataTable.size(); i++) {
			for (int j = 0; j < dataTable.get(i).size(); j++) {
				if (column == j) {
					dataTable.get(i).remove(column);
					break;
				}
			}
		}
	}

	public void aguardarDownloadArquivo() {
		String downloadFilepath = System.getProperty("user.dir") + "/target/temp";
		Utils.waitForFileExistsInPath(downloadFilepath, 10);
		waitMilliseconds(500);
	}

	public void efetuarPrints(String strMensagemLog) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		int intAlturaTotalVertical = Integer
				.valueOf(driver.findElement(By.xpath("//html/body")).getAttribute("scrollHeight"));
		jse.executeScript("scroll(0," + intAlturaTotalVertical + ")");
		int intRolagemVerticalMaxima = Integer
				.valueOf(driver.findElement(By.xpath("//html/body")).getAttribute("scrollTop"));

		int intAlturaVisivelNaTela = intAlturaTotalVertical - intRolagemVerticalMaxima;

		int intQuantidadePrintsVertical = intAlturaTotalVertical / (intAlturaVisivelNaTela - 15);

		for (int i = 0; i < intQuantidadePrintsVertical; i++) {
			jse.executeScript("scroll(0," + (i * (intAlturaVisivelNaTela - 15)) + ")");
			this.log(strMensagemLog);
		}

		jse.executeScript("scroll(0," + (intAlturaTotalVertical) + ")");
		this.log(strMensagemLog);
	}

	public void efetuarPrintsVH(String strMensagemLog) {
		List<WebElement> lstDIVs = driver.findElements(By.xpath(
				"//div[not(@id='cabecalho')][not(@id='cabecalhoPagina')][not(@id='botoesPagina')][not(@id='btnVoltar')][not(@id='exportContainer')][not(@class='classHidden')][not(@class='noExport')][not(@id='loadingScreen')]"));

		List<WebElement> lstDIVsSelecionadas = new ArrayList<WebElement>();
		WebElement DIVSelecionado = null;
		int intAlturaMaxima = 0;
		int intAlturaVisivel = 0;
		int intLarguraMaxima = 0;
		int intLarguraVisivel = 0;
		for (WebElement webElement : lstDIVs) {
			intLarguraMaxima = Integer.valueOf(webElement.getAttribute("scrollWidth"));
			intLarguraVisivel = Integer.valueOf(webElement.getAttribute("clientWidth"));
			intAlturaMaxima = Integer.valueOf(webElement.getAttribute("scrollHeight"));
			intAlturaVisivel = Integer.valueOf(webElement.getAttribute("clientHeight"));

			if ((intLarguraMaxima - intLarguraVisivel > 10) && webElement.getCssValue("overflow").equals("auto")) {
				lstDIVsSelecionadas.add(webElement);
			}
		}
		if (lstDIVsSelecionadas.size() == 1) {
			DIVSelecionado = lstDIVsSelecionadas.get(0);
			intLarguraMaxima = Integer.valueOf(DIVSelecionado.getAttribute("scrollWidth"));
			intLarguraVisivel = Integer.valueOf(DIVSelecionado.getAttribute("clientWidth"));
			intAlturaMaxima = Integer.valueOf(DIVSelecionado.getAttribute("scrollHeight"));
			intAlturaVisivel = Integer.valueOf(DIVSelecionado.getAttribute("clientHeight"));
		} // considera o iframeContent
		else if (lstDIVsSelecionadas.size() == 0) {
			for (WebElement webElement : lstDIVs) {
				DIVSelecionado = webElement;
				if (Integer.valueOf(DIVSelecionado.getAttribute("scrollWidth")) > intLarguraMaxima) {
					intLarguraMaxima = Integer.valueOf(DIVSelecionado.getAttribute("scrollWidth"));
				}
				if (Integer.valueOf(DIVSelecionado.getAttribute("scrollHeight")) > intAlturaMaxima) {
					intAlturaMaxima = Integer.valueOf(DIVSelecionado.getAttribute("scrollHeight"));
				}
			}
			driver.switchTo().defaultContent();
			intAlturaVisivel = Integer.valueOf(driver.findElement(By.id("iframeContent")).getAttribute("clientHeight"));
			intLarguraVisivel = Integer.valueOf(driver.findElement(By.id("iframeContent")).getAttribute("clientWidth"));
			driver.switchTo().frame("iframeContent");
			DIVSelecionado = null; // pois nenhum DIV sera utilizado na rolagem de pagina
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		int intQuantidadePrintsVertical = intAlturaMaxima / intAlturaVisivel;
		int intQuantidadePrintsHorizontal = intLarguraMaxima / intLarguraVisivel;

		// considera o /html/body
		if (DIVSelecionado == null) {
			DIVSelecionado = driver.findElement(By.xpath("/html/body"));

			for (int i = 0; i <= intQuantidadePrintsVertical; i++) {
				for (int j = 0; j <= intQuantidadePrintsHorizontal; j++) {
					jse.executeScript(
							"scroll(" + (j * (intLarguraVisivel - 25)) + "," + (i * (intAlturaVisivel - 15)) + ")");
					this.log(strMensagemLog);
				}
			}
			// considera o div com overflow = auto
		} else {
			// posiciona no topo a esquerda
			jse.executeScript("document.getElementById('" + DIVSelecionado.getAttribute("id") + "').scrollDown = 0 ");
			jse.executeScript("document.getElementById('" + DIVSelecionado.getAttribute("id") + "').scrollLeft = 0 ");
			this.log(strMensagemLog);

			for (int i = 0; i < intQuantidadePrintsVertical; i++) {
				for (int j = 0; j < intQuantidadePrintsHorizontal; j++) {
					jse.executeScript("document.getElementById('" + DIVSelecionado.getAttribute("id")
							+ "').scrollLeft += " + (intLarguraVisivel - 25) + ";");
					this.log(strMensagemLog);
				}
				jse.executeScript("document.getElementById('" + DIVSelecionado.getAttribute("id") + "').scrollDown += "
						+ (intAlturaVisivel - 15) + ";");
			}
		}
	}

	public void clearTextField() {
		sikuli.type("a", Keys.CTRL);
		sikuli.type(Key.DELETE);
	}

	public Location posicionarMouseCentroTela() {
		Location centerOfScreen = sikuli.getScreen().getCenter();
		try {
			sikuli.mouseMove(centerOfScreen);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
		return centerOfScreen;
	}

	public List<List<String>> csvToDataTable(String csv, String separator) {
		List<List<String>> dataTable = new ArrayList<List<String>>();
		List<String> lstLinhas = Arrays.asList(csv.split("\\r?\\n"));
		for (String linha : lstLinhas) {
			dataTable.add(Arrays.asList(linha.split(separator)));
		}
		return dataTable;
	}

	private boolean areDataTablesEquals(List<List<String>> tabelaResultado, List<List<String>> tabelaEsperada) {
		int quantLinhas = tabelaEsperada.size();
		int quantColunas = tabelaEsperada.get(0).size();
		boolean tabelasSaoIguais = true;

		for (int i = 1; i < quantLinhas; i++) {
			for (int j = 1; j < quantColunas; j++) {

				String valorTabelaResultado = tabelaResultado.get(i).get(j);
				String valorTabelaEsperada = tabelaEsperada.get(i).get(j);
				valorTabelaResultado = valorTabelaResultado.replace("\n", " ").trim();
				valorTabelaEsperada = valorTabelaEsperada.replace("\n", " ").trim();
				if (valorTabelaEsperada.equals(valorTabelaResultado)) {
					logPass("O valor <b>" + valorTabelaEsperada + "</b> foi apresentado corretamente. (Lin " + (i)
							+ " Col " + (j) + ")");
				} else {

					System.out.println("Esperado : " + valorTabelaEsperada);
					System.out.println("Resultad : " + valorTabelaResultado);
					logFail("O valor <b>" + valorTabelaEsperada
							+ "</b> retornou diferente do esperado! Valor retornado: [<b>" + valorTabelaResultado
							+ "</b>] (Lin " + (i) + " Col " + (j) + ")");
					tabelasSaoIguais = false;
				}
			}
		}
		return tabelasSaoIguais;
	}

	protected void jmxConsoleExecute(String domain, String service) {
		Utils.httpPost("http://pixadmin:8600@" + Utils.getTestProperty("pacs.server") + "/jmx-console/HtmlAdaptor",
				"action=invokeOp&name=" + domain + ":service=" + service + "&methodIndex=0");
	}

	public boolean isImageEquals(String expectedImage, String resultImagePath) throws IOException {
		Finder resultImage = new Finder(ImageIO.read(new File(resultImagePath)));
		File file = new File(resultImagePath);
		File file2 = new File(expectedImage);

		extentTest.info("Expected image: ",
				MediaEntityBuilder.createScreenCaptureFromPath(file2.getAbsolutePath()).build());
		extentTest.info("Result image: ",
				MediaEntityBuilder.createScreenCaptureFromPath(file.getAbsolutePath()).build());
		resultImage.find(expectedImage);

		if (resultImage.hasNext()) {
			Double comparisonScore = resultImage.next().getScore();

			boolean isImageEquals = comparisonScore > 0.70;

			if (isImageEquals) {
				log("Completely equal images. Measured similarity: " + comparisonScore);
				return true;
			} else {
				logPrintFail("Images not equals. Measured similarity: " + comparisonScore);
				return false;
			}
		}

		logPrintFail("Completely different images.");
		return false;
	}

	public boolean compararStringDesconsiderandoAcentuacao(String a, String b) {
		final Collator instance = Collator.getInstance();
		instance.setStrength(Collator.NO_DECOMPOSITION);

		return instance.compare(a, b) == 0 ? true : false;
	}

}