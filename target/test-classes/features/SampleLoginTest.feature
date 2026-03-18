@SampleLoginTest
Feature: Sample Login Test
  
  As a QA Engineer
  I want to automate Sample Login Test
  So that I can ensure quality
  
  Scenario: Execute Sample Login Test
    Given I navigate to "https://www.google.com"
    When I execute step 1: "Navigate to https://www.google.com"
    When I execute step 2: "navigate to Google homepage"
    When I execute step 3: "verify Google logo is visible"
    When I execute step 4: "verify search box is present"
    When I execute step 5: "enter "baby doll" in the search box"
    When I execute step 6: "click on the search box"
    Then the test should complete successfully
