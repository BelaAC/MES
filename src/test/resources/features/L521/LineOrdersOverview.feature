
Feature: MES - L521 - Line Orders Overview

@Medium-priority
@Packaging
@L521
@LineOrdersOverview
	Scenario: Quality and Periodical PTPs Counter - L521
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L521
		Then I verify if the PTPs counter is correct
		
		