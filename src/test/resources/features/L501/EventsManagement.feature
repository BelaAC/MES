#language: pt
#encoding: utf-8
Funcionalidade: MES - L501 - EventsManagements

@Low-priority
@Packaging
@L501
@EventsManagements
	Cenario: Check Events Radio button	- L501
		E I login as Low Priority ADM in "Packaging"
		E I select the line L501
		E I select the Events button
		Então I verify if each radiobutton Events table is being displayed 

@High-priority
@Packaging
@L501
@EventsManagements
	Cenario: Create, Categorize, Split and Delete a Event	- L501
		E I login as High Priority ADM in "Packaging"	
		E I select the line L501
		E I create a New Downtime
		E I split a Event
		E I categorize a Event
		Então I delete the Event
@test		
@High-priority
@Packaging
@L501
@EventsManagements
	Cenario: Multi Categorize and Delete Event - L501
		E I login as High Priority ADM in "Packaging"		
		E I select the line L501
		E I multi categorize
		Então I multi delete two events

@High-priority
@Packaging
@L501
@EventsManagements	
	Cenario: Translation - GU QA - L501 
		E I login as High Priority ADM in "Packaging"		
		E I select the line L501
		E I select the start date as last month
		Então I check if all the events category are correct
		
@High-priority
@Packaging		
@L501
@EventsManagements
	Cenario: Filter working - GU QA - L501
		E I login as High Priority ADM in "Packaging"		
		E I select the line L501
		E I select the Events button
		Então I check if the filter is working