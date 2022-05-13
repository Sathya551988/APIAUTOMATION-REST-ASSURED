
@tag
Feature: To Verify USERS API Automation with BDD Cucumber Rest Assured
 
  #Get_Method_without_Authourization
  @test
  Scenario: Validate and test the request without Authorization
    Given User leaves the username and password empty
    When The user sends the get request
    Then The user should see the error mesage with the status 400 unauthorized
 #Get_Method_with_Authourization
 @test
  Scenario: Validate and test the request with Authorization
     Given User enters the correct username and password
     When The user sends the get request
     Then The user should see the Status: 200 OK
     And  Check Response status line for Users
     Then Check Response body for Users
     And  Validate Schema for Users
    
