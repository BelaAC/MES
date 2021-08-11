package com.everis.steps;

import org.openqa.selenium.support.PageFactory;

import com.everis.util.TestRule;

import io.cucumber.java.Before;

public class Hooks{
	
	public Hooks() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	@Before
	public void InitializeTest() throws Exception
	{
		// Open Application
		//GU QA
		TestRule.openApplicationChrome("http://172.18.188.25/TS/Account/LogOn.aspx?ts_deny=true&ts_set=%3aPage&ts_item=%2fTS%2fpages%2fabi%2f&ts_action=%3aPage&ts_rurl=%2fTS%2fpages%2fabi%2f%3fts_rurl%3d");
		//SAZ Eval
		//TestRule.openApplicationChrome("http://172.18.188.31/TS/Account/LogOn.aspx");
	}
}
