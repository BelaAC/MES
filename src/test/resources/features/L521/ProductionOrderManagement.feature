
Feature: MES - L521 - ProductionOrderManagement

@High-priority
@Packaging
@L521
@CompletedTest
@ProductionOrderManagement
	Scenario: Create, Edit, Start and End a Order - L521
		And I login as High Priority ADM in "Packaging"
		And I select the line L521
		And I create a new Order
		And I edit the Order
		And I start the Order
		Then I end the Order

@High-priority
@Packaging
@L521
@ProductionOrderManagement
	Scenario: Delete Order - L521
		And I login as High Priority ADM in "Packaging"
		And I select the line L521
		Then I delete the Order
		
@High-priority
@Packaging
@L521
@ProductionOrderManagement
	Scenario: Cancel Order - L521
		And I login as High Priority ADM in "Packaging"
		And I select the line L521
		Then I cancel the Order

@Packaging
@L521			
@ignore
@ProductionOrderManagement
	Scenario: Initiate an Order at the Past	- L521
		And I login as High Priority ADM in "Packaging"
		And I select the line L521
		And I click the button "Abrir Lote"
		And I click the button "Início"	
		And I capture the Order Number in the Start Order Page
		And I change the start date for yesterday
		And I click the button "Início"

@High-priority
@Packaging
@L521	
@ProductionOrderManagement
	Scenario: Planned Orders Filter	- L521
		And I login as High Priority ADM in "Packaging"
		And I select the line L521
		And I click the button "Abrir Lote"
		And I filter the last month orders
		Then I verify if all planned orders are displayed

