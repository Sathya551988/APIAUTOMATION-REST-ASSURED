@feature
Feature: To Verify SKILLS API Automation with BDD Cucumber Rest Assured
  
  #Get Method without Authourization
   @test1
  Scenario: Validate and Test the request without Authorization
    Given SkillsUser leaves the username and password empty
    When SkillsUser sends the Get Request
    Then SkillsUser should see the error message with the Status 401 unauthorized
  #Get Method with Authourization
  @test2
  Scenario: Validate and Test the request with Authorization
    Given SkillsUser enters correct username and password 
    When SkillsUser sends the Get Request
    Then SkillsUser should see the Status:200 OK
    And Check Response status line for skills
    And Check Response body for skills
    And Validate skills Schema
    
    
  