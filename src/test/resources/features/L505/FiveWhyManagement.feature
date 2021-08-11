
Feature: MES - L505 - FiveWhyManagements

@Medium-priority
@Packaging
@L505
@FiveWhy
	Scenario: Create, Deleted and Edit a Five Why - L505
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L505
		And I create and delete a Five Why 
		Then I edit a Five Why

@Low-priority
@Packaging
@L505
@FiveWhy
	Scenario: Filter Five Why - L505
		And I login as Low Priority ADM in "Packaging"
		And I select the line L505
		Then I filter the line Five Whys

