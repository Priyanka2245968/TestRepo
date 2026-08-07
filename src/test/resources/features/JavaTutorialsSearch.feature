@JavaTutorialsSearch
Feature: Search for Java Tutorials on GeeksforGeeks
  
  As a learner
  I want to search for Java tutorials on GeeksforGeeks
  So that I can learn Java programming
  
  Scenario: Search for Java Tutorials
    Given I navigate to "https://www.geeksforgeeks.org/"
    When I execute step 1: "Open browser and go to GeeksforGeeks homepage"
    When I execute step 2: "Click on the search input box"
    When I execute step 3: "Type 'Java tutorials' into the search box"
    When I execute step 4: "Press Enter to submit the search"
    When I execute step 5: "Wait for the search results page to fully load"
    When I execute step 6: "Click the first search result link"
    When I execute step 7: "Wait for the article page to fully load"
    When I execute step 8: "Expand the 'Java Basics' section"
    Then the test should complete successfully