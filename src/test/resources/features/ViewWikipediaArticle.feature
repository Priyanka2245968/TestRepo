@ViewWikipediaArticle
Feature: View Wikipedia Article

  Scenario: View Article on Any Topic
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "Photosynthesis" topic
    And I open the "Photosynthesis" article
    Then The "Photosynthesis" article page is displayed

  Scenario: Invalid Input - Blank Search
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "" topic
    Then An error message "Please enter a topic to search" appears

  Scenario: Boundary Case - Maximum Search Length
    Given I navigate to "https://www.wikipedia.org/"
    When I search for "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultricies lectus eu mauris pharetra, eget luctus felis porta. Nulla facilisi. Fusce quis mauris lectus. Nullam in sem nec magna auctor lacinia eget nec leo. Proin vulputate cursus ipsum." topic
    Then The search results page loads with results matching the entered text