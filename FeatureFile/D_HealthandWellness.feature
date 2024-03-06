Feature: Health and Wellness
 
  Scenario: verify Schedule a demo button navigation
  	Given User click on For Corporate button
  	When User click on Health and Wellness button
  	And User verify schedule button with Invalid Contact Number
  	And verify schedule button with with invalid Email Id
  	And verify schedule button with with Valid Information
  	Then verify Thankyou message is displayed or not
  	And User tear down the complete process