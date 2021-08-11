package com.everis.pages.packaging;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class EditPackagingOrderPage extends BasePage {
	@FindBy(id = "InputPlannedProductionCount")
	protected WebElement plannedProduction;

	@FindBy(id = "FormButtons_Delete")
	protected WebElement deleteButton;

	public EditPackagingOrderPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}
	/**
	 * Edit the Production Count to a random number less than 5 greater than 0
	 * @throws Exception
	 */
	public void editPorductionCount() throws Exception {
		Random random = new Random();
		int productionCount = random.nextInt(5)+ 1;
		String newPlannedProduction = Integer.toString(productionCount);
		plannedProduction.clear();
		plannedProduction.sendKeys(newPlannedProduction);
		dataMap.put("PlannedProduction", newPlannedProduction);
		log("Input " + productionCount + " in Planned Production");
	}

	//Click the Delete Button
	public void clickingDeletButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", deleteButton);
		deleteButton.click();
	}
}