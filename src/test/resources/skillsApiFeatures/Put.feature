
@feature11
Feature: To Verify SKILLS API Automation with Cucumber BDD Rest Assured
   

  @test8
  Scenario: Validate and Test the request with Authorization
  	Given UpdataSkills Read Data from file for put
    When User sent Put request for update Skills endpoint
    Then UpdateSkills Check Response with status code
    And  UpdateSkills Check Response status line
    And  UpdateSkills Validate Put Schema
    And  UpdateSkills response body Validation 
    And  UpdateSkills validate DB 
    @test1
  Scenario: Validate and Test the request without Authorization
    Given Update Skills user leaves the username and password empty
    When Update Skills user sends the Post Request
    Then Update Skills user should see the error message with the Status 401 unauthorized
 