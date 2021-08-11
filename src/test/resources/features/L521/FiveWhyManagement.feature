
Feature: MES - L521 - FiveWhyManagements

@Medium-priority
@Packaging
@L521
@FiveWhy
	Scenario: Create, Deleted and Edit a Five Why - L521
		And I login as Medium Priority ADM in "Packaging"	
		And I select the line L521
		And I create and delete a Five Why 
		Then I edit a Five Why
		
@Low-priority
@Packaging
@L521
@FiveWhy
	Scenario: Filter Five Why - L521
		And I login as Low Priority ADM in "Packaging"	
		And I select the line L521
		Then I filter the line Five Whys