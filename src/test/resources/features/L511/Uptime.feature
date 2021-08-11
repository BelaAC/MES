
Feature: MES - L511 - Uptime

@Low-priority
@Packaging
@L511
@UpTime
	Scenario: Uptime Graphics - L511
		And I login as Low Priority ADM in "Packaging"
		And I select the line L511
		And I select the Line L511 Uptime
		Then I verify if all graphics are working 
	
		
		