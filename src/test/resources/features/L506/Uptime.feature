
Feature: MES - L506 - Uptime

@Low-priority
@Packaging
@L506
@UpTime
	Scenario: Uptime Graphics - L506
		And I login as Low Priority ADM in "Packaging"
		And I select the line L506
		And I select the Line L506 Uptime
		Then I verify if all graphics are working 
	
		
		