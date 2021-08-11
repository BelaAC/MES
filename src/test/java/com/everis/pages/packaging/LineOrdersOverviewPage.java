package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class LineOrdersOverviewPage extends BasePage {

	public LineOrdersOverviewPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//select[@id='cmbShift']")
	protected WebElement shiftCombobox;

	@FindBy(id = "btnRefresh_Refresh")
	protected WebElement refreshButton;

	@FindBy(xpath = "//*[@id='p1-contentPage_ChartSpecResults']")
	protected WebElement canvas;

	/**
	 * Check if the Quality PTPs remaining number is the same as the one in the
	 * cockpit
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyQualityRemaining() throws Exception {
		if (!isElementDisplayed(By.id("NoBatchNumber"))) {
			// Select shift as All
			Select shift = new Select(shiftCombobox);
			shift.selectByVisibleText("[ Todos ]");
			// Refresh the Page
			refreshButton.click();

			waitForPageToLoad(driver);

			// Get the canvas number
			String canvasData = canvas.getText();

			// Turning String into Ints
			int canvaDataInt = Integer.parseInt(canvasData);
			int dashboardValueInt = Integer.parseInt(dashboardValue);
			int outOfSoecDashboardValueInt = Integer.parseInt(outOfSoecDashboardValue);

			// Checking if the numbers are correct
			if (canvaDataInt == dashboardValueInt + outOfSoecDashboardValueInt) {

				log("The remaining PTP's in the line overview and the one in the Order Page are equal");
				// Go back to the Dashboard page
				driver.navigate().back();
				driver.navigate().back();
				return true;
			} else {
				logFail("The remaining PTP's in the line overview is different from the one in the Order Page");
				// Go back to the Dashboard page
				driver.navigate().back();
				driver.navigate().back();
				return false;
			}
		} else {
			log("The Line has no running Order");
			// Go back to the Dashboard page
			driver.navigate().back();
			driver.navigate().back();
		}
		return true;
	}
}