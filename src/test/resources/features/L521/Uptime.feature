
Feature: MES - L521 - Uptime

@Low-priority
@Packaging
@L521
@UpTime
	Scenario: Uptime Graphics - L521
		And I login as Low Priority ADM in "Packaging"	
		And I select the line L521
		And I select the Line L521 Uptime
		Then I verify if all graphics are working 
	
		
		