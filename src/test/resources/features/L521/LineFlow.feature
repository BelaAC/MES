
Feature: MES - L521 - LineFlow

@Medium-priority
@Packaging
@L521
@LineFlow
	Scenario: Verify Disconnected Tags - L521
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L521
		And I select the Line L521 Flow
		Then I verify if all the disconnected TAGs are disconnected 
		
		