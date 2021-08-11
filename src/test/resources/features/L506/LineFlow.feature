
Feature: MES - L506 - LineFlow

@Medium-priority
@Packaging
@L506
@LineFlow
	Scenario: Verify Disconnected Tags - L506
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L506
		And I select the Line L506 Flow
		Then I verify if all the disconnected TAGs are disconnected 
		
		