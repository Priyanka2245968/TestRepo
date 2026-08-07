@Login
Feature: Login Functionality

  As a user
  I want to login to the secure area
  So that I can access restricted content

  Scenario: Successful Login
    Given I navigate to "https://the-internet.herokuapp.com/login"
    When I execute step 1: "Navigate to the login page"
    When I execute step 2: "Enter username in the username field"
    When I execute step 3: "Enter password in the password field"
    When I execute step 4: "Click the Login button"
    Then the test should complete successfully