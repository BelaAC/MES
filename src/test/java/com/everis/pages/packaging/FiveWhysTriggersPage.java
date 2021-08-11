package com.everis.pages.packaging;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class FiveWhysTriggersPage extends BasePage {

	public FiveWhysTriggersPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='Table_CustomProperties']//tr[2]//a[contains(text(),'L501')]")
	protected WebElement firstSystemL501;

	@FindBy(xpath = "//*[@id='Table_CustomProperties']//tr[14]//a[contains(text(),'L505')]")
	protected WebElement firstSystemL505;

	@FindBy(xpath = "//*[@id='Table_CustomProperties']//tr[24]//a[contains(text(),'L506')]")
	protected WebElement firstSystemL506;

	@FindBy(xpath = "//*[@id='Table_CustomProperties']//tr[36]//a[contains(text(),'L521')]")
	protected WebElement firstSystemL521;

	@FindBy(xpath = "//*[@id='Table_CustomProperties']//tr[49]//a[contains(text(),'L511')]")
	protected WebElement firstSystemL511;

	@FindBy(xpath = "//*[@id='Table_CustomProperties']//tr[55]//a[contains(text(),'L536')]")
	protected WebElement firstSystemL536;

	// Select the first System for the Line L501
	public void selectFirstSystemL501() throws Exception {
		String systemL501 = firstSystemL501.getText();
		firstSystemL501.click();
		dataMap.put("System", systemL501);
		log("Selected the L501 system: " + systemL501);
	}

	// Select the first System for the Line L505
	public void selectFirstSystemL505() throws Exception {
		String systemL505 = firstSystemL505.getText();
		clickElementJS(driver, firstSystemL505);
		;
		dataMap.put("System", systemL505);
		log("Selected the L505 system: " + systemL505);
	}

	// Select the first System for the Line L506
	public void selectFirstSystemL506() throws Exception {
		String systemL506 = firstSystemL506.getText();
		clickElementJS(driver, firstSystemL506);
		;
		dataMap.put("System", systemL506);
		log("Selected the L506 system: " + systemL506);
	}

	// Select the first System for the Line L521
	public void selectFirstSystemL521() throws Exception {
		String systemL521 = firstSystemL521.getText();
		clickElementJS(driver, firstSystemL521);
		;
		dataMap.put("System", systemL521);
		log("Selected the L521 system: " + systemL521);
	}

	// Select the first System for the Line L511
	public void selectFirstSystemL511() throws Exception {
		String systemL511 = firstSystemL511.getText();
		clickElementJS(driver, firstSystemL511);
		;
		dataMap.put("System", systemL511);
		log("Selected the L511 system: " + systemL511);
	}

	// Select the first System for the Line L536
	public void selectFirstSystemL536() throws Exception {
		String systemL536 = firstSystemL536.getText();
		clickElementJS(driver, firstSystemL536);
		;
		dataMap.put("System", systemL536);
		log("Selected the L536 system: " + systemL536);
	}

	public void insertRandomNumberFiveWhy(String combobox) {
		WebElement selectedCombox = driver
				.findElement(By.xpath("//label[contains(text(),'" + combobox + "')]//following-sibling::input"));
		selectedCombox.clear();
		Random random = new Random();
		int random5Why = random.nextInt(20) + 1;
		String newRandom5Why = Integer.toString(random5Why);
		selectedCombox.sendKeys(newRandom5Why);
		newRandom5Why.toString();
		dataMap.put(combobox, newRandom5Why);
	}

	public boolean verifySystemPropertiesEdit() {
		String uptimeValue = driver
				.findElement(By.xpath("//a[contains(text(),'" + dataMap.get("System") + "')]/ancestor::tr/td[7]"))
				.getText();
		String dowtimeValue = driver
				.findElement(By.xpath("//a[contains(text(),'" + dataMap.get("System") + "')]/ancestor::tr/td[8]"))
				.getText();

		if (uptimeValue.equals(dataMap.get("Uptime Target")) && dowtimeValue.equals(dataMap.get("Downtime"))) {
			log("The System Properties were successfully Edited");
			return true;
		}
		logFail("The System Properties were not successfully edited");
		return false;
	}
}