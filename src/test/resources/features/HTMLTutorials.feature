@HTMLTutorials
Feature: Search for HTML Tutorials on W3Schools
  
  As a learner
  I want to search for HTML tutorials on W3Schools
  So that I can learn HTML

  Scenario: Search for HTML Tutorials
    Given I navigate to "https://www.w3schools.com"
    When I click on the search box
    And I enter "HTML" in the search box
    And I press Enter to submit the search
    Then search results should be displayed
    And HTML-related tutorials should be listed
    And no error message should appear