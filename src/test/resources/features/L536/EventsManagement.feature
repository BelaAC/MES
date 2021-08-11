
Feature: MES - L536 - EventsManagements

@Low-priority
@Packaging
@L536
@EventsManagements
	Scenario: Check Events Radio button	- L536
		And I login as Low Priority ADM in "Packaging"
		And I select the line L536
		And I select the Events button
		Then I verify if each radiobutton Events table is being displayed 	

@High-priority
@Packaging
@L536
@EventsManagements
	Scenario: Create, Categorize, Split and Delete a Event	- L536
		And I login as High Priority ADM in "Packaging"
		And I select the line L536
		And I create a New Downtime
		And I split a Event
		And I categorize a Event
		Then I delete the Event

@High-priority
@Packaging
@L536
@EventsManagements
	Scenario: Multi Categorize and Delete Event - L536
		And I login as High Priority ADM in "Packaging"
		And I select the line L536
		And I multi categorize
		Then I multi delete two events

@High-priority
@Packaging
@L536
@EventsManagements	
	Scenario: Translation - GU QA - L536
		And I login as High Priority ADM in "Packaging"
		And I select the line L536
		And I select the start date as last month
		Then I check if all the events category are correct
		
@High-priority
@Packaging		
@L536
@EventsManagements
	Scenario: Filter working - GU QA - L536
		And I login as High Priority ADM in "Packaging"	
		And I select the line L536
		And I select the Events button
		Then I check if the filter is working