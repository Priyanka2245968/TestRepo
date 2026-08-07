@HTMLTutorial
Feature: View HTML Tutorial on W3Schools
  
  As a learner
  I want to access the HTML Tutorial on W3Schools
  So that I can learn HTML

  Scenario: Navigate to HTML Tutorial
    Given I navigate to "https://www.w3schools.com"
    When I execute step 1: "Navigate to W3Schools homepage"
    When I execute step 2: "Click the 'HTML' link in the top navigation bar"
    When I execute step 3: "Wait for HTML Tutorial page to load"
    Then the HTML Tutorial page should load successfully
    And the HTML Tutorial heading should be visible
    And the HTML HOME navigation should be highlighted