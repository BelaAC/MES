
Feature: MES - L501 - Line Orders Overview

@Medium-priority
@Packaging
@L501
@LineOrdersOverview
	Scenario: Quality and Periodical PTPs Counter - L501
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L501
		Then I verify if the PTPs counter is correct
		
		