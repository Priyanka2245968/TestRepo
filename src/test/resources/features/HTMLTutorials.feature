@HTMLTutorials
Feature: Search for HTML tutorials on W3Schools
  
  As a learner
  I want to search for HTML tutorials on W3Schools
  So that I can learn HTML

  Scenario: Search for HTML tutorials
    Given I navigate to "https://www.w3schools.com"
    When I execute step 1: "Navigate to W3Schools website" 
    When I execute step 2: "Click on the search input box at the top"
    When I execute step 3: "Enter the keyword 'HTML' in the search box"
    When I execute step 4: "Press Enter key to submit the search"
    Then the test should complete successfully