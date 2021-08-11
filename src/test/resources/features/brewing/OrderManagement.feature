
Feature: MES - OrderManagement

@High-priority
@Brewing
@OrderManagement
	Scenario: Open, Edit, Starts and Close a Batch		
		And I login as High Priority ADM in "Processo Cerveja"	
		And I open a new batch
		And I start the batch
		And I edit the batch
		Then I close the batch

@Medium-priority
@Brewing
@OrderManagement
	Scenario: Cancel a Batch		
		And I login as Medium Priority ADM in "Processo Cerveja"	
		And I open a new batch
		And I start the batch
		Then I cancel the batch

@High-priority
@Brewing
@OrderManagement	
Scenario: Add, Edit and Cancel Materials 
		And I login as High Priority ADM in "Processo Cerveja"
		And I add, edit and cancel a material

@Low-priority
@Brewing
@BendTool	
Scenario: BendTool		
		And I login as Low Priority ADM in "Processo Cerveja"
		And I go to the "Maturação" BlendTool
		And I select two batches
		Then I should see the Blend