
Feature: MES - L505 - Line Orders Overview

@Medium-priority
@Packaging
@L505
@LineOrdersOverview
	Scenario: Quality and Periodical PTPs Counter - L505
		And I login as Medium Priority ADM in "Packaging"
		And I select the line L505
		Then I verify if the PTPs counter is correct
		
		