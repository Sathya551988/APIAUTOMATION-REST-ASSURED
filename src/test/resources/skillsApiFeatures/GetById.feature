@feature1
  Feature: To Verify SKILLS API Automation with BDD Cucumber Rest Assured
   #Get Id Method without Authourization
   @test1
  Scenario: Validate and Test the request without Authorization
    Given SkillsUser leaves the username and password empty
    When SkillsUser sends the Get Request
    Then SkillsUser should see the error message with the Status 401 unauthorized
    #Get Method with Single Skills with Authorization
  @test2
  Scenario: Validate and Test the request for the single Skill
    Given Skills user enters username and password 
    When Skills user sends the Get Request for the Single skill by "891"
    Then Skills user should see the Status:200 OK for single id
    And  Check Response status line for get skills
    And Check Response body for get skills
    And  Validate get skills Schema
    
    