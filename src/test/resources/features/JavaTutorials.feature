@JavaTutorials
Feature: Java Tutorials Test
  
  As a QA Engineer
  I want to automate Java Tutorials Test
  So that I can ensure quality
  
  Scenario: Execute Java Tutorials Test
    Given I navigate to "https://www.geeksforgeeks.org/"
    When I execute step 1: "Open browser and go to GeeksforGeeks homepage"
    When I execute step 2: "Click on the Search input box"
    When I execute step 3: "Type Java tutorials into the search box"
    When I execute step 4: "Press Enter to submit the search"
    When I execute step 5: "Wait for the search results page to fully load"
    When I execute step 6: "Click the first result link"
    When I execute step 7: "Wait for the article page to fully load"
    When I execute step 8: "Expand the Java Basics section in the left sidebar"
    Then the test should complete successfully