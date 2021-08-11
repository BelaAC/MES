
Feature: MES - L511 - LineFlow

@Medium-priority
@Packaging
@L511
@LineFlow
	Scenario: Verify Disconnected Tags - L511
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L511
		And I select the Line L511 Flow
		Then I verify if all the disconnected TAGs are disconnected 
		
		