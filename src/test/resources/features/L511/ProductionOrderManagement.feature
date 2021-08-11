
Feature: MES - L511 - ProductionOrderManagement

@High-priority
@Packaging
@L511
@ProductionOrderManagement
	Scenario: Create, Edit, Start and End a Order - L511
		And I login as High Priority ADM in "Packaging"
		And I select the line L511
		And I create a new Order
		And I edit the Order
		And I start the Order
		Then I end the Order 

@High-priority
@Packaging
@L511
@ProductionOrderManagement
	Scenario: Delete Order - L511
		And I login as High Priority ADM in "Packaging"
		And I select the line L511
		Then I delete the Order

@High-priority
@Packaging
@L511
@ProductionOrderManagement
	Scenario: Cancel Order - L511
		And I login as High Priority ADM in "Packaging"
		And I select the line L511
		Then I cancel the Order

@Packaging	
@ignore
@L511
@ProductionOrderManagement
	Scenario: Initiate an Order at the Past - L511	
		And I login as High Priority ADM in "Packaging"	
		And I select the line L511
		And I click the button "Abrir Lote"
		And I click the button "Início"	
		And I capture the Order Number in the Start Order Page
		And I change the start date for yesterday
		And I click the button "Início"

@High-priority
@Packaging
@L511	
@ProductionOrderManagement
	Scenario: Planned Orders Filter	- L511
	And I login as High Priority ADM in "Packaging"
		And I select the line L511
		And I click the button "Abrir Lote"
		And I filter the last month orders
		Then I verify if all planned orders are displayed

