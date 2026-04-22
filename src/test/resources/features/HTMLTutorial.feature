@HTMLTutorial
Feature: HTML Tutorial Test
  
  As a QA Engineer
  I want to automate HTML Tutorial Test
  So that I can ensure quality
  
  Scenario: Execute HTML Tutorial Test
    Given I navigate to "https://www.w3schools.com/"
    When I execute step 1: "Navigate to W3Schools homepage"
    When I execute step 2: "Enter 'HTML Tutorial' in the search box"
    When I execute step 3: "Click on the search result for 'HTML Tutorial'"
    When I execute step 4: "Scroll through the tutorial content"
    Then the test should complete successfully