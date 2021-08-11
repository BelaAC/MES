
Feature: MES - L506 - Line Orders Overview

@Medium-priority
@Packaging
@L506
@LineOrdersOverview
	Scenario: Quality and Periodical PTPs Counter - L506
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L506
		Then I verify if the PTPs counter is correct
		