
Feature: MES - L536 - Uptime

@Low-priority
@Packaging
@L536
@UpTime
	Scenario: Uptime Graphics - L536
		And I login as Low Priority ADM in "Packaging"
		And I select the line L536
		And I select the Line L536 Uptime
		Then I verify if all graphics are working 
	
		
		