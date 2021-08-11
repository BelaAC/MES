package com.everis.pages.packaging;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class StartPackagingOrdePage extends BasePage {

	@FindBy(xpath = "//span[normalize-space()='Nome do Lote']//following-sibling::span")
	protected WebElement orderNumberStart;

	@FindBy(id = "inputStartDate_Date")
	protected WebElement inputStartDate;
	
	public StartPackagingOrdePage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}
	//Gets Order Number in the Start Page to compare after 
	public void captureOrderNumberInStartPage() throws Exception {
		String OrderNumber = orderNumberStart.getText();
		dataMap.put("StartedOrder", OrderNumber);
	}
	
	private Date getMeYesterday(){
	     return new Date(System.currentTimeMillis()-24*60*60*1000);
	}
	
	/**
	 * Select yesterday as Start Date
	 * @throws Exception
	 */
	public void changingStartDate() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		String newDate = format.format(getMeYesterday());
		inputStartDate.clear();
		inputStartDate.sendKeys(newDate);
		log("Input the date: " + newDate + " in the Start Date field");
	}
}