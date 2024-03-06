Feature: Find Doctors
 
  Scenario Outline: Select City and Profession
    Given User navigate to find Doctors page
    When User search for city                           
  	And User search for profession
  	Then User click on profession
  	
  Scenario: Filter doctors and extract doctors details
  	Given User select Patient Stories from dropdown
  	And User select Experience from dropdown
		And User select Fees and Availability from All Filters
		Then User select Sort By from the dropdown 
		And User display the doctors details