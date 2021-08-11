package com.everis.pages.packaging;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class LineFlowPage extends BasePage {

	public LineFlowPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//a[@href='/TS/pages/abi/config/']")
	protected WebElement configuration;

	@FindBy(xpath = "//a[normalize-space()='OPC Tag Status']")
	protected WebElement tagStatus;

	@FindBy(xpath = "//a[normalize-space()='Tags']")
	protected WebElement tag;

	@FindBy(id = "FlowChartPH")
	protected WebElement flowChart;

	/**
	 * Gets all the disconnected TAGs
	 * 
	 * @throws Exception
	 */
	public void verifyTAGS() throws Exception {
		List<WebElement> disconnectedTags = new ArrayList<WebElement>();
		List<String> disconnectedTagsTitles = new ArrayList<String>();
		disconnectedTags = driver.findElements(By
				.xpath("//*[@id='FlowChartPH']//span[2]/span//../../..//span[contains(@class, 'floating-box-title')]"));
		disconnectedTags = driver.findElements(By.xpath(
				"//span[contains(text(), 'Disconected')]//..//..//..//../span[contains(@class, 'floating-box-title')]"));

		for (WebElement webElement : disconnectedTags) {
			scrollToElementJS(driver, webElement);
			String disconnectedTagTitle = webElement.getText();
			disconnectedTagsTitles.add(disconnectedTagTitle);
			log("The Tag: " + disconnectedTagTitle + " is currently disconnect");
		}

		if (disconnectedTagsTitles.size() == 0) {
			scrollToElementJS(driver, flowChart);
			log("The Line has no disconnected Tags");
		}
	}
}