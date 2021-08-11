package com.everis.pages.packaging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.everis.pages.BasePage;
import com.everis.util.TestRule;

public class PackagingMainPage extends BasePage {

	public PackagingMainPage() {

		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@FindBy(xpath = "//a[@href='/TS/pages/abi/pack/FiveWhys']")
	protected WebElement fiveWhyButton;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL01']//a[contains(text(),'L501')]")
	protected WebElement l501;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL05']//a[contains(text(),'L505')]")
	protected WebElement l505;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL06']//a[contains(text(),'L506')]")
	protected WebElement l506;

	@FindBy(xpath = "//li[@id='MainPack01PACK_KEG_KEG521']//a[contains(text(),'L521')]")
	protected WebElement l521;

	@FindBy(xpath = "//li[@id='MainPack01PACK_CAN_CAN511']//a[contains(text(),'L511')]")
	protected WebElement l511;

	@FindBy(xpath = "//li[@id='MainPack01PACK_FULLFLEX_FULLFLEX536']//a[contains(text(),'L536')]")
	protected WebElement l536;

	@FindBy(xpath = "//*[@id='MainPack01PACK_BTL_BTL01']//a[@title='lineflow']")
	protected WebElement l501Flow;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL05']//a[normalize-space()='Fluxograma']")
	protected WebElement l505Flow;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL06']//a[normalize-space()='Fluxograma']")
	protected WebElement l506Flow;

	@FindBy(xpath = "//li[@id='MainPack01PACK_KEG_KEG521']//a[normalize-space()='Fluxograma']")
	protected WebElement l521Flow;

	@FindBy(xpath = "//li[@id='MainPack01PACK_CAN_CAN511']//a[normalize-space()='Fluxograma']")
	protected WebElement l511Flow;

	@FindBy(xpath = "//li[@id='MainPack01PACK_FULLFLEX_FULLFLEX536']//a[normalize-space()='Fluxograma']")
	protected WebElement l536Flow;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL01']//a[@title='uptime'][normalize-space()='EP']")
	protected WebElement l501Uptime;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL05']//a[@title='uptime'][normalize-space()='EP']")
	protected WebElement l505Uptime;

	@FindBy(xpath = "//li[@id='MainPack01PACK_BTL_BTL06']//a[@title='uptime'][normalize-space()='EP']")
	protected WebElement l506Uptime;

	@FindBy(xpath = "//li[@id='MainPack01PACK_CAN_CAN511']//a[@title='uptime'][normalize-space()='EP']")
	protected WebElement l511Uptime;

	@FindBy(xpath = "//li[@id='MainPack01PACK_KEG_KEG521']//a[@title='uptime'][normalize-space()='EP']")
	protected WebElement l521Uptime;

	@FindBy(xpath = "//li[@id='MainPack01PACK_FULLFLEX_FULLFLEX536']//a[@title='uptime'][normalize-space()='EP']")
	protected WebElement l536Uptime;

	@FindBy(xpath = "//a[@href='/TS/pages/abi/pack/']")
	protected WebElement packaging;

	public void checkPackagingTranslated() {
		packaging.click();
	}

	// Select Line 501
	public void selectLine501() throws Exception {
		l501.click();
		log("Selected the Line: L501");
		dataMap.put("Line", "L501");
	}

	// Select Line 505
	public void selectLine505() throws Exception {
		l505.click();
		log("Selected the Line: L505");
		dataMap.put("Line", "L505");
	}

	// Select Line 506
	public void selectLine506() throws Exception {
		l506.click();
		log("Selected the Line: L506");
		dataMap.put("Line", "L506");
	}

	// Select Line 521
	public void selectLine521() throws Exception {
		l521.click();
		log("Selected the Line: L521");
		dataMap.put("Line", "L521");
	}

	// Select Line 511
	public void selectLine511() throws Exception {
		clickElementJS(driver, l511);
		log("Selected the Line: L511");
		dataMap.put("Line", "L511");
	}

	// Select Line 536
	public void selectLine536() throws Exception {
		l536.click();
		log("Selected the Line: L536");
		dataMap.put("Line", "L536");
	}

	// Select Line 501 Flow
	public void selectLine501Flow() throws Exception {
		l501Flow.click();
		log("Selected the Line L501 Flow");
	}

	// Select Line 505 Flow
	public void selectLine505Flow() throws Exception {
		l505Flow.click();
		log("Selected the Line L505 Flow");
	}

	// Select Line 506 Flow
	public void selectLine506Flow() throws Exception {
		l506Flow.click();
		log("Selected the Line L506 Flow");
	}

	// Select Line 521 Flow
	public void selectLine521Flow() throws Exception {
		l521Flow.click();
		log("Selected the Line L521 Flow");
	}

	// Select Line 511 Flow
	public void selectLine511Flow() throws Exception {
		l511Flow.click();
		log("Selected the Line L511 Flow");
	}

	// Select Line 536 Flow
	public void selectLine536Flow() throws Exception {
		l536Flow.click();
		log("Selected the Line L536 Flow");
	}

	// Select Line 501 Uptime
	public void selectLine501Uptime() throws Exception {
		l501Uptime.click();
		log("Selected the Line L501 Uptime");
	}

	// Select Line 505 Uptime
	public void selectLine505Uptime() throws Exception {
		l505Uptime.click();
		log("Selected the Line L505 Uptime");
	}

	// Select Line 506 Uptime
	public void selectLine506Uptime() throws Exception {
		l506Uptime.click();
		log("Selected the Line L506 Uptime");
	}

	// Select Line 511 Uptime
	public void selectLine511Uptime() throws Exception {
		l511Uptime.click();
		log("Selected the Line L511 Uptime");
	}

	// Select Line 521 Uptime
	public void selectLine521Uptime() throws Exception {
		l521Uptime.click();
		log("Selected the Line L521 Uptime");
	}

	// Select Line 536 Uptime
	public void selectLine536Uptime() throws Exception {
		l536Uptime.click();
		log("Selected the Line L536 Uptime");
	}

	// Select Orders List View
	public void selectOrdersListView() throws Exception {
		driver.findElement(By.xpath("//a[@href='/TS/pages/abi/pack/Overview']")).click();
		log("Selected the Orders List View");
	}

	// Select Five Whys
	public void selectFiveWhys() throws Exception {
		fiveWhyButton.click();
		log("Selected Five Why");
	}
}