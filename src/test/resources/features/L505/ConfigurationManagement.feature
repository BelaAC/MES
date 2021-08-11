
Feature: MES - L505 - Configuration Management

@Packaging
@L505
@ignore
@ConfigurationManagement
	Scenario: Able Products - L505 	
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L505
		And I verify the possibles options for the line
		Then I check if they are all able in the Product Matrix