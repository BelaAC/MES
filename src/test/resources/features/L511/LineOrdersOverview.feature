
Feature: MES - L511 - Line Orders Overview

@Medium-priority
@Packaging
@L511
@LineOrdersOverview
	Scenario: Quality and Periodical PTPs Counter - L511
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L511
		Then I verify if the PTPs counter is correct