
Feature: MES - TransferOut

@High-priority
@Brewing
@TransferOut
	Scenario: Transfer Out for every equipment		
		And I login as High Priority ADM in "Processo Cerveja"	
		Then I transfer out for every equipment

@High-priority
@Brewing
@TransferOut
	Scenario: Transfer Out for Tanks		
		And I login as High Priority ADM in "Processo Cerveja"
		And I create a yeast sample for "fermentation"
		And I transfer yeast recovery
		And I crop yeast
		And I spent yeast
		Then I transfer out

@High-priority
@Brewing		
@TransferOut
	Scenario: Transfer Out for Unitanks		
		And I login as High Priority ADM in "Processo Cerveja"
		And I create a yeast sample for "unitank"
		And I transfer yeast recovery
		And I crop yeast
		And I spent yeast
		Then I transfer out

@High-priority
@Brewing		
@TransferOut
	Scenario: Transfer Out for Yeast Storage Tanks	
		And I login as High Priority ADM in "Processo Cerveja"
		And I create a yeast sample for "crop yeast tank"
		And I crop yeast
		And I spent yeast
		And I transfer yeast recovery
		Then I transfer Yeast pitching to fermentation and unitank

@Medium-priority
@Brewing		
@TransferOut
	Scenario: Transfer Out for Yeast Recovery Tanks	
		And I login as Medium Priority ADM in "Processo Cerveja"
		And I create a yeast sample for "yeast recovery tank"
		And I transfer yeast recovery to maturation
		Then I spent and check spent yeast

@High-priority
@Brewing 
@TransferOut 
	Scenario: Transfer Out to Filtration
		And I login as High Priority ADM in "Processo Cerveja"
		And I transfer from maturation to filtration
		And I transfer from "PVPP" to filtration
		And I transfer from "DAW" to filtration
		Then I transfer to TPs
		
@High-priority
@Brewing
@TransferOut
	Scenario: Transfer Out from TPs
	 	And I login as High Priority ADM in "Processo Cerveja"
	 	And I transfer to re-filtration
	 	And I transfer from tank to tank
	 	Then I transfer to packaging