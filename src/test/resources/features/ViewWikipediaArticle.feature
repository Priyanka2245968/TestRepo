@ViewWikipediaArticle
Feature: View Article on Wikipedia

  As a user
  I want to search for and view an article on Wikipedia
  So that I can learn about a topic

  Scenario: Happy Path - View Article on Wikipedia
    Given I navigate to Wikipedia
    When I search for "Python programming language"
    And I click on the "Python (programming language)" article link
    Then I should see the "Python (programming language) - Wikipedia" article page

  Scenario: Negative Case - Invalid Search Query
    Given I navigate to Wikipedia
    When I search for "@#$%^&*()"
    Then I should see a message indicating no results found

  Scenario: Negative Case - Article Not Found
    Given I navigate to Wikipedia
    When I search for "Non-existent article title"
    And I click on the "Non-existent article title" article link
    Then I should see a message indicating the article does not exist