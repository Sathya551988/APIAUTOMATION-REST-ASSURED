
@feature12
Feature: To Verify Skill API Automation with Cucumber BDD Rest Assured
   I want to use this template for my SkillApi Put User feature file

  @test1
  Scenario: Validate and Test the request with Authorization
  	Given Read Data from file for DELETESkills
    When User sent DELETE request to delete the User
    Then DeleteSkills Check Response with status
    And  DeleteSkills Check Response status line
    And  DeleteSkill Validate delete Schema
    And  DeleteSkill validate DB
  @test1
  Scenario: Validate and Test the request without Authorization
    Given Delete Skills user leaves the username and password empty
    When Delete Skills user sends the Delete Request
    Then Delete Skills user should see the error message with the Status 401 unauthorized