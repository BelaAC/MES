
Feature: MES - L536 - FiveWhyManagements

@Medium-priority
@Packaging
@L536
@FiveWhy
	Scenario: Create, Deleted and Edit a Five Why - L536
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L536
		And I create and delete a Five Why 
		Then I edit a Five Why

@Low-priority
@Packaging
@L536
@FiveWhy
	Scenario: Filter Five Why - L536
		And I login as Low Priority ADM in "Packaging"
		And I select the line L536
		Then I filter the line Five Whys

