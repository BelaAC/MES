
Feature: MES - L505 - Uptime

@Low-priority
@Packaging
@L505
@UpTime
	Scenario: Uptime Graphics - L505
		And I login as Low Priority ADM in "Packaging"	
		And I select the line L505
		And I select the Line L505 Uptime
		Then I verify if all graphics are working 
	
		
		