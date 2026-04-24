@HTMLTutorial
Feature: View HTML Tutorial on W3Schools
  
  As a learner
  I want to access the HTML Tutorial on W3Schools
  So that I can learn HTML

  Scenario: Navigate to HTML Tutorial
    Given I navigate to "https://www.w3schools.com/"
    When I execute step 1: "Navigate to W3Schools homepage"
    When I execute step 2: "Click on the 'TUTORIALS' link in the top navigation menu" 
    When I execute step 3: "In the sidebar under 'Content', expand the section"
    When I execute step 4: "Click on the 'HTML Tutorial' link"
    When I execute step 5: "On the HTML Tutorial page, click the 'Start learning HTML now' button"
    Then the test should complete successfully