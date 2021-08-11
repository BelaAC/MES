package com.everis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.util.TestRule;

public class ProductMatrixPage extends BasePage {

	public ProductMatrixPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//a[@href='/TS/pages/abi/config/']")
	protected WebElement configuration;

	@FindBy(xpath = "//a[normalize-space()='Matriz de Produtos']")
	protected WebElement productMatrix;

	@FindBy(xpath = "//input[@id='FilterShowOnlyEnabled']")
	protected WebElement filterEnabled;

	@FindBy(id = "Refresh_Button")
	protected WebElement refreshButton;

	/**
	 * Check if all the products displayed to the user to create a new Order as able
	 * to be used
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyOptionInProductMatrix() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", configuration);
		productMatrix.click();

		Select productSetCombox = new Select(
				driver.findElement(By.xpath("//select[@id='DropDown_Productset_Filter']")));
		productSetCombox.selectByVisibleText("Returnable Bottles (Glass)");
		Select areaCombox = new Select(driver.findElement(By.xpath("//select[@id='DropDown_Area_Filter']")));
		areaCombox.selectByVisibleText(dataMap.get("Line"));
		refreshButton.click();
		int productOptionsSize = productOptionText.size();

		for (int i = 0; i <= productOptionsSize - 1; i++) {
			String machineEnabled = driver.findElement(By.xpath("//tbody//a[contains(text(), '"
					+ dataMap.get("Line") + "')]//ancestor::tr//a[contains(text(), '" + productOptionText.get(i)
					+ "')]//ancestor::tr//td[10]")).getText();

			if (!(machineEnabled.equals("1"))) {
				logFail("The Option: " + productOptionText.get(i)
						+ " is being shown in the Orders Product Options but the same option is unable in the Configurations");
				return false;
			}
		}
		log("All the option shown in the Orders Product Options are Able to be use");
		productOptionText.clear();
		return true;
	}
}