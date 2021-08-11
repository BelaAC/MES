
Feature: MES - General - OrdersListView

@High-priority
@General
@OrdersListView
	Scenario: Access Events		
		And I login as High Priority ADM in "Packaging"	
		And I select Orders List View
		Then I check if all PTPs options are working

@Low-priority
@General
@ignore
@OrdersListView
	Scenario: Perform all filters	
		And I login as Low Priority ADM in "Packaging"
		And I select Orders List View
		And I filter the last week orders
		Then I verify if all orders referring to the period of time are shown

		