
Feature: MES - L505 - ProductionOrderManagement

@High-priority
@Packaging
@L505
@ProductionOrderManagement
	Scenario: Create, Edit, Start and End a Order - L505
		And I login as High Priority ADM in "Packaging"	
		And I select the line L505
		And I create a new Order
		And I edit the Order
		And I start the Order
		Then I end the Order 

@High-priority
@Packaging
@L505
@ProductionOrderManagement
	Scenario: Delete Order - L505
		And I login as High Priority ADM in "Packaging"	
		And I select the line L505
		Then I delete the Order

@High-priority
@Packaging
@L505
@ProductionOrderManagement
	Scenario: Cancel Order - L505
		And I login as High Priority ADM in "Packaging"
		And I select the line L505
		Then I cancel the Order

@Packaging
@L505	
@ignore
@ProductionOrderManagement
	Scenario: Initiate an Order at the Past	- L505
		And I login as High Priority ADM in "Packaging"
		And I select the line L505
		And I click the button "Abrir Lote"
		And I click the button "Início"	
		And I capture the Order Number in the Start Order Page
		And I change the start date for yesterday
		And I click the button "Início"

@High-priority
@Packaging
@L505
@ProductionOrderManagement
	Scenario: Planned Orders Filter	- L505
		And I login as High Priority ADM in "Packaging"
		And I select the line L505
		And I click the button "Abrir Lote"
		And I filter the last month orders
		Then I verify if all planned orders are displayed

@High-priority
@Packaging
@L505
@ProductionOrderManagement
	Scenario: Old Orders Records - L505
		And I login as High Priority ADM in "Packaging"
		And I select the line L505
		Then I select the first Old Order	
		
		