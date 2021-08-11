
Feature: MES - L501 - Uptime

@Low-priority
@Packaging
@L501
@UpTime
	Scenario: Uptime Graphics - L501
		And I login as Low Priority ADM in "Packaging"
		And I select the line L501
		And I select the Line L501 Uptime
		Then I verify if all graphics are working 
	
		
		