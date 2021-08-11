
Feature: MES - General - Configuration Management

@Low-priority
@Packaging
@ConfigurationManagement
	Scenario: Min Delay Seconds	
		And I login as ADM in Configuration
		And I go to the Line configurations
		Then I verify all the system "Bottling"

@Low-priority
@Packaging
@L501
@ignore
@ConfigurationManagement
	Scenario: Able Products - L501 	
		And I login as Low Priority ADM in "Packaging"
		And I select the line L501
		And I verify the possibles options for the line
		Then I check if they are all able in the Product Matrix

@Translated
@ConfigurationManagement
	Scenario: Translated Test 	
		And I login as the translated user
		And I check the Packaging area
		  