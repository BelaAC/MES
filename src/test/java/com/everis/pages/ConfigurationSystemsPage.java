package com.everis.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.util.TestRule;

public class ConfigurationSystemsPage extends BasePage {

	public ConfigurationSystemsPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(id = "tseditcp_SYSDEF_STATE_MIN_DELAY")
	protected WebElement minDelay;

	@FindBy(xpath = "//a[normalize-space()='AB InBev']/..")
	protected WebElement abInBev;

	@FindBy(xpath = "//a[normalize-space()='Site']")
	protected WebElement site;

	@FindBy(xpath = "//div[@id= 'contentPage_slice1_TreeList_Tree']//a[contains(text(),'Packaging')]")
	protected WebElement packaging;

	@FindBy(xpath = "//a[normalize-space()='Bottling']")
	protected WebElement bottling;

	@FindBy(css = "#tsslice-index-3 > ul > li > a")
	protected WebElement edit;

	@FindBy(xpath = "//a[normalize-space()='System Attributes']")
	protected WebElement systemAttributes;

	@FindBy(xpath = "//a[normalize-space()='Cancelar']")
	protected WebElement cancelButton;

	public static int i = 1;

	/**
	 * Check if the min Delay is displayed to the user
	 * 
	 * @param system
	 * @throws Exception
	 */
	public void selectSystem(String system) throws Exception {
		// Select the Line
		List<WebElement> Lines = new ArrayList<WebElement>();
		Lines = driver.findElements(By.xpath("//a[contains(text(),'L50')]"));

		// Get the correct Line xpath
		for (int i = 0; i <= Lines.size(); i++) {
			switch (i) {
			case 0:
				driver.findElement(By.xpath("//a[normalize-space()='L501']")).click();
				break;
			case 1:
				driver.findElement(By.xpath("//a[normalize-space()='L505']")).click();
				break;
			case 2:
				driver.findElement(By.xpath("//a[normalize-space()='L506']")).click();
				break;
			default:
				break;
			}

			// Getting all the options for the system
			List<WebElement> systemArray = new ArrayList<>();
			systemArray = driver.findElements(
					By.xpath("//*[@id='tsslice-content-2']//li[position()>1]//*[contains(text(),'" + system + "')]"));
			log("Select system:  " + system);

			// There are always two not useful options
			int systemSize = systemArray.size() - 2;
			int j = 1;
			// Go to only useful options
			while (j <= systemSize) {
				// Get the WebElement
				WebElement lineSystem = driver.findElement(By.xpath("//*[@id='tsslice-content-2']//li[position()>" + j
						+ "]//*[contains(text(),'" + system + "')]"));
				// The system name 
				String selectedSystem = lineSystem.getText();
				// Go to the system
				lineSystem.click();

				dataMap.put("SelectedSystem", selectedSystem);
				wait(3);
				// Go to edit the system 
				edit.click();
				// Got to the System Attributes
				systemAttributes.click();

				// Go to the min delay configuration
				scrollToElementJS(driver, minDelay);
				// Get the min dalay value
				String MinDelaySeconds = minDelay.getAttribute("value");
				j++;
				log("The Min Delay in Seconds for the system: " + dataMap.get("SelectedSystem") + " is: "
						+ MinDelaySeconds);
				// Go back to the Bottling page 
				cancelButton.click();
			}
		}

	}

	/**
	 * This method goes to the Line Configurations
	 * 
	 * @throws Exception
	 */
	public void goLineConfiguartions() throws Exception {
		// Go to the line configurations
		abInBev.click();
		wait(2);
		// Select site
		site.click();
		wait(2);
		// Select Packaging
		packaging.click();
		wait(2);
		// Select Bottling
		bottling.click();
		wait(2);
	}

}