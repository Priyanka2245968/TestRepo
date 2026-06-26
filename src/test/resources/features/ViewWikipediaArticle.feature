@ViewWikipediaArticle
Feature: View Wikipedia Article

  Scenario: Search for a topic and view the corresponding Wikipedia article
    Given I navigate to Wikipedia
    When I search for "Python programming language"
    And I click on the "Python (programming language)" article link
    Then the "Python (programming language)" article page should load successfully