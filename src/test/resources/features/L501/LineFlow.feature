
Feature: MES - L501 - LineFlow

@Medium-priority
@Packaging
@L501
@LineFlow
	Scenario: Verify Disconnected Tags - L501
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L501
		And I select the Line L501 Flow
		Then I verify if all the disconnected TAGs are disconnected 
	
		
		