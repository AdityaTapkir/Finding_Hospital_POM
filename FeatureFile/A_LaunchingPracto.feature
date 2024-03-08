Feature: Practo Page

  Scenario: UrlCheck
    Given User do the basic Setup
    When User get Url from the Properties file
  	Then Verify the Url
 
	Scenario: Verify Find Doctors button
		Given User is on practo page
		When User check if the find doctors is displayed
		Then if displayed click on find doctors