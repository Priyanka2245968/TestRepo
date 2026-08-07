@JavaTutorials
Feature: Java Tutorials Test
  
  As a QA Engineer
  I want to automate Java Tutorials Test
  So that I can ensure quality
  
  Scenario: Execute Java Tutorials Test
    Given I navigate to "https://www.geeksforgeeks.org/"
    When I execute step 1: "Open browser and go to GeeksforGeeks"
    When I execute step 2: "Click on the Search input box"
    When I execute step 3: "Type Java tutorials"
    When I execute step 4: "Press Enter"
    When I execute step 5: "Wait for the search results page to fully load"
    When I execute step 6: "Click the first result link: "Learn Java - A Beginners Guide for 2024 - GeeksforGeeks""
    When I execute step 7: "Wait for the article page to fully load"
    When I execute step 8: "Expand "Java Basics""
    Then the test should complete successfully