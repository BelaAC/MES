package com.everis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.util.TestRule;

public class LoginPage extends BasePage {

	@FindBy(id = "LoginEtsButton")
	protected WebElement singInButton;

	@FindBy(xpath = "//input[@id='username']")
	protected WebElement username;

	@FindBy(id = "password")
	protected WebElement password;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-chevron-right']")
	protected WebElement navRight;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/config/']")
	protected WebElement configuration;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/rsazzz/']")
	protected WebElement sazReports;
	
	public LoginPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	/**
	 * Login as High Priority ADM in the chosen Module
	 * 
	 * @param moduleButton - System module
	 * @throws Exception
	 */
	public void loginAsHighPriorityADM(String moduleButton) throws Exception {
		waitForPageToLoad(driver);
		// SAZ Eval
		// username.sendKeys("pcgauto");
		// GU QA
		username.sendKeys("automationUserHighPriority");
		password.sendKeys("a1");
		// password.sendKeys("pcgauto");
		singInButton.click();
		driver.findElement(By.xpath("//div[normalize-space()='" + moduleButton + "']/..")).click();
		log("Login as ADM in: " + moduleButton);
	}
	
	/**
	 * Login as Medium Priority ADM in the chosen Module
	 * 
	 * @param moduleButton - System module
	 * @throws Exception
	 */
	public void loginAsMediumPriorityADM(String moduleButton) throws Exception {
		waitForPageToLoad(driver);
		// SAZ Eval
		// username.sendKeys("pcgauto");
		// GU QA
		username.sendKeys("automationUserMediumPriority");
		password.sendKeys("a1");
		// password.sendKeys("pcgauto");
		singInButton.click();
		driver.findElement(By.xpath("//div[normalize-space()='" + moduleButton + "']/..")).click();
		log("Login as ADM in: " + moduleButton);
	}
	
	/**
	 * Login as Low Priority ADM in the chosen Module
	 * 
	 * @param moduleButton - System module
	 * @throws Exception
	 */
	public void loginAsLowPriorityADM(String moduleButton) throws Exception {
		waitForPageToLoad(driver);
		// SAZ Eval
		// username.sendKeys("pcgauto");
		// GU QA
		username.sendKeys("automationUserLowPriority");
		password.sendKeys("a1");
		// password.sendKeys("pcgauto");
		singInButton.click();
		driver.findElement(By.xpath("//div[normalize-space()='" + moduleButton + "']/..")).click();
		log("Login as ADM in: " + moduleButton);
	}

	/**
	 * Login as AMD in the configuration
	 * 
	 * @throws Exception
	 */
	public void loginAsADMinConfiguration() throws Exception {
		waitForPageToLoad(driver);
		// GU QA
		username.sendKeys("autouser");
		password.sendKeys("a1");
		singInButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", configuration);
	}
	
	/**
	 * Login as AMD in the configuration
	 * 
	 * @throws Exception
	 */
	public void loginAsTranslatedUser() throws Exception {
		waitForPageToLoad(driver);
		// GU QA
		username.sendKeys("userautotranslated");
		password.sendKeys("a1");
		singInButton.click();
	}

	/**
	 * Login as AMD in SAZ Report
	 * 
	 * @throws Exception
	 */
	public void loginAsADMinSAZReports() throws Exception {
		waitForPageToLoad(driver);
		// GU QA
		username.sendKeys("autouser");
		password.sendKeys("a1");
		singInButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", sazReports);
	}
}