
Feature: MES - L501 - FiveWhyManagements

@Medium-priority
@Packaging
@L501
@FiveWhy
	Scenario: Create, Deleted and Edit a Five Why - L501
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L501
		And I create and delete a Five Why 
		Then I edit a Five Why

@Low-priority 
@Packaging
@L501
@FiveWhy
	Scenario: Filter Five Why - L501
		And I login as Low Priority ADM in "Packaging"
		And I select the line L501
		Then I filter the line Five Whys
