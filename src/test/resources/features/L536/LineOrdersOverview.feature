
Feature: MES - L536 - Line Orders Overview

@Medium-priority
@Packaging
@L536
@LineOrdersOverview
	Scenario: Quality and Periodical PTPs Counter - L501
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L536
		Then I verify if the PTPs counter is correct
		