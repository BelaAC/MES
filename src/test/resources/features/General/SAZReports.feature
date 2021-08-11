
Feature: MES - General - SAZ Reports

@High-priority
@General
@SAZReports
	Scenario: Export Reports
		And I login as ADM in SAZ Reports	
		And I select the option "Planilhas Packaging"
		Then I export the Reports

@High-priority
@General		
@SAZReports
	Scenario: Export Stops and Production Reports
		And I login as ADM in SAZ Reports	
		And I select the option "Export Paradas e Produção"
		Then I export the Report
	
		
		