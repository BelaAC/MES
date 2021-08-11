package com.everis.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.util.TestRule;

public class SAZReportsPage extends BasePage {

	public SAZReportsPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "Export_Export")
	protected WebElement exportButton;

	@FindBy(xpath = "//*[@id='ParentAreas']//li/span[1]")
	protected WebElement checkAllButton;

	@FindBy(xpath = "//span[@class='tsoperation-item-key-tsmenuitem_0']")
	protected WebElement checkAllLines;

	@FindBy(id = "ButtonRefresh_Button")
	protected WebElement refreshButton;

	@FindBy(id = "Solicitar_Solicitar")
	protected WebElement requestButton;

	/**
	 * Select a Report Option
	 * 
	 * @param reportOption - Report Option
	 */
	public void selectReportOption(String reportOption) {
		driver.findElement(By.xpath("//a[normalize-space()='" + reportOption + "']")).click();
		log("Selected the option: " + reportOption);
	}

	/**
	 * Export the Report and check if the download was successful
	 * 
	 * @throws Exception
	 */
	public void exportReports() throws Exception {
		for (int i = 1; i <= 2; i++) {
			WebElement selectedReport = driver.findElement(By.xpath("//*[@id='Planilha']/option[" + i + "]"));
			String selectedReportName = selectedReport.getText();
			selectedReport.click();
			dataMap.put("SelectedReport", selectedReportName);

			for (int j = 2; j <= 4; j++) {
				WebElement selectedLine = driver
						.findElement(By.xpath("//*[@id='SystemDropDownList']/option[" + j + "]"));
				String selectedLineName = selectedLine.getText();
				selectedLine.click();
				dataMap.put("SelectedLine", selectedLineName);
				log("Selected the Report: " + selectedReportName + " and the Line: " + selectedLineName);
				exportButton.click();

				switch (selectedReportName) {
				case "Parada Producao Gargalo":
					selectedReportName = "Paradas Linha (Gargalos)";
					break;

				case "Paradas Todos Equipamentos":
					selectedReportName = "Todas Paradas";
					break;

				default:
					break;
				}

				// Select the Download Path to the Export
				File spreadsheet = new File(downloadPath + "\\" + selectedReportName + ".xlsx");
				boolean finished = false;
				int attempt = 0;

				// Check if the download is happening
				while (finished == false) {
					if (attempt < 20) {
						long a = spreadsheet.length();
						Thread.sleep(500);
						long b = spreadsheet.length();
						if (b > a)
							attempt++;
						else
							finished = true;
					}
				}

				// Check if the File exists
				Assert.assertTrue(spreadsheet.exists());
				// Delete the File
				Assert.assertTrue(spreadsheet.delete());
				log("Successfully downloaded the report" + selectedReportName + " for the Line: " + selectedLineName);
			}
		}
	}

	public boolean exportStopAndProductionReport() throws Exception {
		List<WebElement> lastRequests = new ArrayList<WebElement>();
		lastRequests = driver.findElements(By.xpath("//*[@id='LastRequestsTable']//tbody/tr"));
		int lastRequestsCount = lastRequests.size();

		checkAllButton.click();
		clickElementJS(driver, refreshButton);

		checkAllLines.click();
		requestButton.click();
		lastRequests = driver.findElements(By.xpath("//*[@id='LastRequestsTable']//tbody/tr"));
		int lastestRequestsCount = lastRequests.size();

		if (lastestRequestsCount > lastRequestsCount) {
			log("Successfully requested the export");
			return true;

		} else {
			log("The system was not able to request the export");
			return false;
		}
	}
}