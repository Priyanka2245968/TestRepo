@Login
Feature: Login Functionality

  Scenario: Successful Login
    Given I navigate to the login page
    When I enter username "tomsmith"
    And I enter password "SuperSecretPassword!"
    And I click the Login button
    Then the user should be redirected to the secure area
    And a success flash message should be displayed
    And a Logout button should be visible