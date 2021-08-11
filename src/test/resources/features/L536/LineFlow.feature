
Feature: MES - L536 - LineFlow

@Medium-priority
@Packaging
@L536
@LineFlow
	Scenario: Verify Disconnected Tags - L536
		And I login as Medium Priority ADM in "Packaging"	
		And I select the line L536
		And I select the Line L536 Flow
		Then I verify if all the disconnected TAGs are disconnected 
		
		