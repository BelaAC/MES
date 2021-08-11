package com.everis.pages.packaging;

import java.text.Normalizer;
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

public class PlannedOrdersPage extends BasePage {

	@FindBy(id = "RangeSelect_Start")
	protected WebElement selectStartDate;

	@FindBy(id = "Refresh_Button")
	protected WebElement refreshButton;

	@FindBy(id = "ddProductList_Filter")
	protected WebElement productsComobox;

	public PlannedOrdersPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
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
		boolean isProdutDisplayed = isElementDisplayed(By.xpath("//td[normalize-space()='" + deliveredOrderNumber
				+ "']/..//following-sibling::td[3]/a[contains(text(),'" + dataMap.get("Product") + "')]"));
		boolean isPlannedProductionDisplayed = isElementDisplayed(By.xpath("//td[normalize-space()='"
				+ deliveredOrderNumber + "']/..//following-sibling::td[6]/a[contains(text(),'"
				+ dataMap.get("PlannedProduction") + "')]"));

		if (isOrderNumberDisplayed && isProdutDisplayed && isPlannedProductionDisplayed) {
			log("Delivered the expected Order Number: " + deliveredOrderNumber);
			return true;
		}
		logFail("Did not deliver the expected Order Number: " + deliveredOrderNumber);
		return false;
	}

	/**
	 * Checking if the Deleted Order Number is no more displayed in the Planned
	 * orders screen
	 * 
	 * @throws Exception
	 */
	public boolean checkingDeletedOrder() throws Exception {
		String deliveredOrderNumber = dataMap.get("DeliveredOrderNumber");

		boolean checkingOrderNumber = driver
				.findElements(By.xpath("//a[normalize-space()='" + deliveredOrderNumber + "']")).size() == 0;
		if (checkingOrderNumber) {
			log("The system successfully deleted the Order");
			return true;
		}
		logFail("Did not deleted the Order Number: " + deliveredOrderNumber);
		return false;
	}

	/**
	 * Checking if the changed specifications in a order were updated
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean checkingChangedSpecifications() throws Exception {
		String deliveredOrderNumber = dataMap.get("Delivered Order Number");

		boolean isOrderNumberDisplayed = isElementDisplayed(
				By.xpath("//a[normalize-space()='" + deliveredOrderNumber + "']"));
		boolean isPlannedProductionDisplayed = isElementDisplayed(By.xpath("//td[normalize-space()='"
				+ deliveredOrderNumber + "']/..//following-sibling::td[6]/a[contains(text(),'"
				+ dataMap.get("PlannedProduction") + "')]"));

		if (isOrderNumberDisplayed && isPlannedProductionDisplayed) {
			log("Successfully edited the order: " + deliveredOrderNumber);
			return true;
		}
		logFail("Did not edited the Order Number: " + deliveredOrderNumber);
		return false;
	}

	/**
	 * Select the Filter for the last month
	 */
	public void filterLastMonthOrders() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatDB = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String newDate = format.format(cal.getTime());
		String formatLastMonthDateDB = formatDB.format(cal.getTime());
		selectStartDate.clear();
		selectStartDate.sendKeys(newDate + " 12:00 AM");
		refreshButton.click();
		log("Filtred Planned Orders from: " + newDate);
		dataMap.put("Date", formatLastMonthDateDB);
	}

	public void selectRandomProduct() {
		Select productList = new Select(driver.findElement(By.id("ddProductList_Filter")));
		List<WebElement> itemsInDropdown = productList.getOptions();
		int size = itemsInDropdown.size();
		Random random = new Random();
		int randomNumber = random.nextInt(size);

		if (randomNumber == 0 || randomNumber == 1) {
			randomNumber = 2;
		}
		itemsInDropdown.get(randomNumber).click();
		String selectedProduct = productList.getFirstSelectedOption().getText().trim();
		dataMap.put("SelectedProduct", selectedProduct);
		log("Selected the Product: " + selectedProduct);
	}

	/**
	 * Verify if the same information in the Data Base is the same displayed in the
	 * Planned Orders Page
	 * 
	 * @throws Exception
	 */

	public void verifyLastMonthPlannedOrders() throws Exception {
		Set<String> plannedOrdersNumbers = new HashSet<>();
		ArrayList<DBReturn> returnDAO = ReturnDAO.listInfoFromDBPlannedOrders();

		for (int i = 1; i <= returnDAO.size(); i++) {
			String ordersNumbers = driver
					.findElement(By.xpath("//*[@id='TablePlannedOrders']/div/table/tbody/tr[" + i + "]/td[2]"))
					.getText();
			plannedOrdersNumbers.add(ordersNumbers);
		}

		Object[] auxlList1 = plannedOrdersNumbers.toArray();
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
		log("All Planned Orders in the Data Base [" + bdList.size() + "] are displayed for the user ["
				+ screenList.size() + "]");
	}

	/**
	 * Compares if all the displayed planned orders are related to the chosen random
	 * product in the filter
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean verifyProductOrders() throws Exception {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='TablePlannedOrders']/div/table/tbody/tr"));
		int countRows = rows.size();

		if (countRows == 0) {
			log("The Random Selected Product has no planned orders for the last month");
		} else

			for (int i = 1; i <= countRows; i++) {
				String product = driver
						.findElement(By.xpath("//*[@id='TablePlannedOrders']/div/table/tbody/tr[" + i + "]/td[3]"))
						.getText().trim();

				product = Normalizer.normalize(product, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
				String filterProduct = Normalizer.normalize(dataMap.get("SelectedProduct"), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
				
				if (!product.equalsIgnoreCase(filterProduct)) {
					logFail("Orders unrelated to the filter were displayed");
					return false;
				}
			}
		log("All the displeyd Product are the same chosen for the Product Filter");
		return true;
	}
}
