
Feature: MES - L506 - Configuration Management

@Packaging	
@L506
@ignore
@ConfigurationManagement
	Scenario: Able Products - L506	
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L506
		And I verify the possibles options for the line
		Then I check if they are all able in the Product Matrix
	