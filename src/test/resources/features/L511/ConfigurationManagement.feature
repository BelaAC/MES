
Feature: MES - L511 - Configuration Management

@Packaging
@L511
@CompletedTest
@ConfigurationManagement
	Scenario: Min Delay Seconds L511 	
		And I login as ADM in Configuration
		And I click the button "AB InBev"
		And I click the button "Site"
		And I click the button "Packaging"
		And I click the button "Canning"
		And I click the button "L511"
		Then I verify all the system "Canning"
