@W3SchoolsSearch
Feature: Search for HTML tutorials on W3Schools
  
  As a learner
  I want to search for HTML tutorials on W3Schools
  So that I can learn HTML

  Scenario: Search for HTML tutorials
    Given I navigate to "https://www.w3schools.com"
    When I click on the search box
    And I enter "HTML" in the search box
    And I press Enter key to submit the search
    Then Search results should be displayed
    And Results should contain HTML-related tutorials
    And No error or broken page should appear