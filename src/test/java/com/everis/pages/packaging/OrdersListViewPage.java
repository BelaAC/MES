package com.everis.pages.packaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.dao.ReturnDAO;
import com.everis.model.DBReturn;
import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class OrdersListViewPage extends BasePage {

	@FindBy(id = "cmbResultType_Filter")
	protected WebElement complianceTypeComobox;

	@FindBy(id = "cmbProduct_Filter")
	protected WebElement brandCombobox;

	@FindBy(id = "btnRefresh_Button")
	protected WebElement refreshButton;

	@FindBy(id = "datePicker_Start")
	protected WebElement startDate;

	@FindBy(id = "buttonsTop_Back")
	protected WebElement backButton;

	public OrdersListViewPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Select each PTPs option and verify if the page is correctly displayed
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyPTPOption() throws Exception {
		for (int i = 0; i <= 6; i++) {
			String xpath = null;
			WebElement ptpsOption = driver
					.findElement(By.xpath("//*[@id='tableDiscreteJobs']//tbody/tr[1]/td[" + (12 + i) + "]/a"));
			if (i == 5) {
				ptpsOption.click();
				wait(2);
				if (isElementDisplayed(By.xpath("//span[normalize-space()='Árvore de Rastreabilidade']"))) {
					backButton.click();
					log("The PTPs Option: Geneology is working");
				}
			} else {
				String ptpsOptionName = ptpsOption.getAttribute("title");
				ptpsOption.click();
				waitForPageToLoad(driver);

				switch (ptpsOptionName) {
				case "Process Checks":
					xpath = "//span[normalize-space()='Checks de Processo']";
					break;
				case "Quality Checks (All)":
					xpath = "//span[normalize-space()='Qualidade']";
					break;
				case "Materials added":
					xpath = "//span[normalize-space()='Materiais']";
					break;
				case "Notifications to approve":
					xpath = "//span[normalize-space()='Notificações']";
					break;
				case "Acknowledgements remaining":
					xpath = "//canvas[@id='canvas$stateEvents']";
					break;
				case "Ir para Tela Cockpit":
					xpath = "//span[contains(text(),'Dashboard')]";
					break;
				default:
					break;
				}
				if (!isElementDisplayed(By.xpath(xpath))) {
					logFail("The PTPs Option: " + ptpsOptionName + " is not properly working");
					return false;
				} else {
					log("The PTPs Option: " + ptpsOptionName + " is working");
				}

				driver.findElement(By.xpath("//a[@href='/TS/pages/abi/pack/Overview']")).click();
			}
		}
		return true;
	}

	/**
	 * Select Random Compliance Type
	 */
	public void randomComplianceType() {
		Select complianceTypeList = new Select(driver.findElement(By.id("cmbResultType_Filter")));
		List<WebElement> itemsInDropdown = complianceTypeList.getOptions();
		int size = itemsInDropdown.size();
		Random random = new Random();
		int randomNumber = random.nextInt(size);
		itemsInDropdown.get(randomNumber).click();
		String selectedCompliance = complianceTypeList.getFirstSelectedOption().getText().trim();
		dataMap.put("SelectedCompliance", selectedCompliance);
	}

	/**
	 * Select Random Brand Type
	 */
	public void randomBrand() {
		Select brandList = new Select(driver.findElement(By.id("cmbProduct_Filter")));
		List<WebElement> itemsInDropdown = brandList.getOptions();
		int size = itemsInDropdown.size();
		Random random = new Random();
		int randomNumber = random.nextInt(size);
		itemsInDropdown.get(randomNumber).click();
		String selectedBrand = brandList.getFirstSelectedOption().getText().trim();
		dataMap.put("SelectedBrand", selectedBrand);
	}

	/**
	 * Select Random Criteria for the filter
	 * 
	 * @throws Exception
	 */
	public void selectRandomCriterias() throws Exception {
		randomComplianceType();
		log("Selected the Complaince Type: " + dataMap.get("SelectedCompliance"));
		randomBrand();
		log("Selected the Brand: " + dataMap.get("SelectedBrand") + " and the Complaince Type: "
				+ dataMap.get("SelectedCompliance"));
		clickElementJS(driver, refreshButton);
		log("Clicked the button: Refresh");
	}

	// Get Last Week
	public void filterLastWeekOrders() {
		// Filter Format
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// DataBase Format
		SimpleDateFormat formatDB = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		String newDate = format.format(cal.getTime());
		String formatLastWeekDateDB = formatDB.format(cal.getTime());
		startDate.clear();
		startDate.sendKeys(newDate);
		log("Filtred Planned Orders from: " + newDate);
		dataMap.put("LastWeek", formatLastWeekDateDB);
	}

	/**
	 * Filter Last Week Order in the Page and checks if it is the same as the
	 * DataBase Filter
	 */
	public void verifyLastWeekOrders() {
		Set<String> packingAreaOrderNumber = new HashSet<>();
		ArrayList<DBReturn> returnDAO = ReturnDAO.listInfoFromDBOrdersListView();

		for (int i = 1; i <= returnDAO.size(); i++) {
			String ordersNumbers = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[5]")).getText();
			packingAreaOrderNumber.add(ordersNumbers);
		}

		Object[] auxlList1 = packingAreaOrderNumber.toArray();
		List<String> screenList = new ArrayList<String>();
		for (int i = 0; i < auxlList1.length; i++)
			screenList.add(auxlList1[i].toString());

		Object[] auxlList2 = returnDAO.toArray();
		List<String> bdList = new ArrayList<String>();
		for (int i = 0; i < auxlList2.length; i++)
			bdList.add(auxlList2[i].toString());

		Collections.sort(screenList);
		Collections.sort(bdList);

		Assert.assertEquals(screenList, bdList);
		log("All Orders in the Data Base [" + bdList.size() + "] are displayed for the user [" + screenList.size()
				+ "]");
	}
}
