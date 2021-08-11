
Feature: MES - L511 - FiveWhyManagements

@Medium-priority
@Packaging
@L511
@FiveWhy
	Scenario: Create, Deleted and Edit a Five Why - L511
		And I login as Medium Priority ADM in "Packaging"	
		And I select the line L511
		And I create and delete a Five Why 
		Then I edit a Five Why

@Low-priority
@Packaging
@L511
@FiveWhy
	Scenario: Filter Five Why - L511
		And I login as Low Priority ADM in "Packaging"	
		And I select the line L511
		Then I filter the line Five Whys
