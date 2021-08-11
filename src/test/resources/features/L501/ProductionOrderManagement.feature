
Feature: MES - L501 - ProductionOrderManagement

@High-priority
@Packaging
@L501
@ProductionOrderManagement
	Scenario: Create, Edit, Start and End a Order - L501
		And I login as High Priority ADM in "Packaging"	
		And I select the line L501
		And I create a new Order
		And I edit the Order
		And I start the Order
		Then I end the Order

@High-priority
@Packaging
@L501
@ProductionOrderManagement
	Scenario: Delete Order - L501
		And I login as High Priority ADM in "Packaging"		
		And I select the line L501
		Then I delete the Order

@High-priority
@Packaging
@L501
@ProductionOrderManagement
	Scenario: Cancel Order - L501
		And I login as High Priority ADM in "Packaging"	
		And I select the line L501
		Then I cancel the Order

@Packaging
@L501		
@ignore
@ProductionOrderManagement
	Scenario: Initiate an Order at the Past	- L501
		And I login as High Priority ADM in "Packaging"
		And I select the line L501
		And I click the button "Abrir Lote"
		And I click the button "Início"	
		And I capture the Order Number in the Start Order Page
		And I change the start date for yesterday
		And I click the button "Início"

@High-priority
@Packaging
@L501		
@ProductionOrderManagement
	Scenario: Planned Orders Filter	- L501
		And I login as High Priority ADM in "Packaging"	
		And I select the line L501
		And I click the button "Abrir Lote"
		And I filter the last month orders
		Then I verify if all planned orders are displayed

@Low-priority
@Packaging
@L501
@ProductionOrderManagement
	Scenario: Old Orders Records - L501	
		And I login as Low Priority ADM in "Packaging"
		And I select the line L501
		Then I select the first Old Order	
		