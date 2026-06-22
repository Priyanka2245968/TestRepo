Feature: Wikipedia Search

  Scenario: Search and View Wikipedia Article
    Given I am on the Wikipedia homepage
    When I search for "HTML Tutorial"
    Then I should see the search results page
    When I click the HTML Tutorial link
    Then I should see the HTML Tutorial page

  Scenario: Invalid Input - Empty Search Field
    Given I am on the Wikipedia homepage
    When I search for ""
    Then I should not see the search results page

  Scenario: Boundary Case - Maximum Search Length
    Given I am on the Wikipedia homepage
    When I search for a string with 500 characters
    Then I should see the search results page