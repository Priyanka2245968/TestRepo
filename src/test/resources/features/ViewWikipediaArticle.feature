@ViewArticle
Feature: View Article on Wikipedia

  Scenario: Search and View Python Programming Language Article
    Given I navigate to Wikipedia
    When I search for "Python Programming Language"
    And I click on the Python Programming Language link
    Then I should see the Python Programming Language article
