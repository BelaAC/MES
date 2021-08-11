package com.everis.pages.packaging;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class UptimePage extends BasePage {

	public UptimePage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "dtStartDate")
	protected WebElement startDate;

	@FindBy(id = "btnRefresh_Button")
	protected WebElement refresh;

	@FindBy(id = "LineChart_Uptime")
	protected WebElement canvas;

	/**
	 * Check if the dates in the Uptime Graphics are the same as the ones in the
	 * filters
	 * 
	 * @throws Exception
	 */
	public boolean UptimeGraphicsWorking() throws Exception {
		List<WebElement> lineSystems = new ArrayList<WebElement>();
		selectFilterLastWeek();
		List<String> lineSystemsNames = new ArrayList<String>();
		lineSystems = driver.findElements(By.xpath(
				"//div[@class='col-sm-2 col-tsgridlayout-view']//div[@class='row']//div[@class='tstile-text-small']"));

		for (int i = 1; i <= lineSystems.size(); i++) {
			WebElement system = driver
					.findElement(By.xpath("//*[@id='SystemTiles']/a[" + i + "]//div[@class='tstile-text-small']"));
			String name = system.getText();
			lineSystemsNames.add(name);

			clickElementJS(driver, system);

			String canvasInformation = canvas.getAttribute("data-tschart");
			String canvaDate = StringUtils.substringAfter(canvasInformation, "labels");
			canvaDate = canvaDate.substring(canvaDate.indexOf("[") + 1, canvaDate.indexOf("]")).trim();
			String lastWeekDateCanvas = canvaDate.substring(canvaDate.indexOf("\"") + 1, canvaDate.indexOf(" "));

			canvaDate = canvaDate.substring(0, canvaDate.lastIndexOf("\""));
			String latestCanvaDate = canvaDate.substring(canvaDate.lastIndexOf("\"") + 1, canvaDate.lastIndexOf(" "));

			if (dataMap.get("LastWeek").equals(lastWeekDateCanvas) && latestCanvaDate.equals(today())) {
				log("The Uptime graphics for the: " + name + " is correct");
			} else {
				log("The Uptime graphics for the: " + name + " is not working correctly");
				return false;
			}
		}

		return true;
	}

	// Get today and format to the filter
	public String today() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM");
		LocalDateTime now = LocalDateTime.now();
		String today = format.format(now);
		return today;
	}

	/**
	 * Filter Last Week as the Start Date
	 * 
	 * @throws Exception
	 */
	public void selectFilterLastWeek() throws Exception {
		startDate.click();
		wait(3);
		startDate.clear();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatCanvas = new SimpleDateFormat("dd/MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -6);
		String newDate = format.format(cal.getTime());
		newDate = formatCanvas.format(cal.getTime());
		startDate.sendKeys(newDate);
		dataMap.put("LastWeek", newDate);
		refresh.click();
		waitForPageToLoad(driver);
	}

}