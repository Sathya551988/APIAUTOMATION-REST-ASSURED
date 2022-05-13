@feature10
Feature: To Verify Skills API Automation with Cucumber BDD Rest Assured
   I want to use this template for my SkillsApi Put User feature file

  @test9
  Scenario: Validate and Test the request with Authorization
  	Given CreateSkills Read Data from file for post
    When The user create Skills with POST API
    Then CreateSkills Check Response with status code
    And CreateSkills Check Response status line
    And CreatSkills Validate Post Schema
    And CreatSkills response Validation 
    And CreatSkills validate DB 
    
  @test1
  Scenario: Validate and Test the request without Authorization
    Given Create SkillsUser leaves the username and password empty
    When Create SkillsUser sends the Post Request
    Then Create SkillsUser should see the error message with the Status 401 unauthorized
 