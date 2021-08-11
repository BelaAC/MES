
Feature: MES - L505 - LineFlow

@Medium-priority
@Packaging
@L505
@LineFlow
	Scenario: Verify Disconnected Tags - L505
		And I login as Medium Priority ADM in "Packaging"	
		And I select the line L505
		And I select the Line L505 Flow
		Then I verify if all the disconnected TAGs are disconnected 
		
		