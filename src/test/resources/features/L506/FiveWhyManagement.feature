
Feature: MES - L506 - FiveWhyManagements

@Medium-priority
@Packaging
@L506
@FiveWhy
	Scenario: Create, Deleted and Edit a Five Why - L506
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L506
		And I create and delete a Five Why 
		Then I edit a Five Why

@Low-priority
@Packaging
@L506
@FiveWhy
	Scenario: Filter Five Why - L506
		And I login as Low Priority ADM in "Packaging"
		And I select the line L506
		Then I filter the line Five Whys
