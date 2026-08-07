@HTMLTutorial
Feature: View HTML Tutorial on W3Schools
  
  As a learner
  I want to access the HTML Tutorial on W3Schools
  So that I can learn HTML

  Scenario: Happy Path - View HTML Tutorial
    Given I navigate to "https://www.w3schools.com"
    When I execute step 1: "Navigate to W3Schools homepage"
    When I execute step 2: "Click the 'HTML' link in the top navigation bar" 
    When I execute step 3: "Wait for HTML Tutorial page to load"
    Then the test should complete successfully
    And the HTML Tutorial page loads at "https://www.w3schools.com/html/default.asp"
    And the page heading "HTML Tutorial" is displayed in the main content area
    And the left navigation panel shows "HTML HOME" highlighted under the HTML section