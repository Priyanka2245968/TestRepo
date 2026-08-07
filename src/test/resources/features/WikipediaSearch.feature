@WikipediaSearch
Feature: Wikipedia Search Test
  
  As a QA Engineer
  I want to automate Wikipedia Search Test
  So that I can ensure quality
  
  Scenario: Execute Wikipedia Search Test
    Given I navigate to "https://www.wikipedia.org/"
    When I execute step 1: "Navigate to Wikipedia homepage"
    When I execute step 2: "Click on the search bar"
    When I execute step 3: "Enter a random string 'asdfghjk' in the search bar"
    When I execute step 4: "Clear the search bar"
    When I execute step 5: "Enter a valid topic 'Python (programming language)' in the search bar"
    When I execute step 6: "Press Enter to submit search"
    When I execute step 7: "Click on the top search result titled 'Python (programming language)'"
    When I execute step 8: "Attempt to click on a non-existent link on the page"
    When I execute step 9: "Scroll down to a section in the article"
    When I execute step 10: "Right-click on the page and select 'View Page Source'"
    When I execute step 11: "Close the Page Source window"
    Then the test should complete successfully