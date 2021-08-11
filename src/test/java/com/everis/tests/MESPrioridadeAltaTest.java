package com.everis.tests;

import org.junit.ClassRule;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import com.everis.util.TestRule;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@Brewing", glue = {
		"" }, monochrome = true, dryRun = false, plugin = { "json:target/cucumber.json", "rerun:target/rerun.txt" })
public class MESPrioridadeAltaTest {

	@ClassRule
	public static TestRule testRule = new TestRule();
}