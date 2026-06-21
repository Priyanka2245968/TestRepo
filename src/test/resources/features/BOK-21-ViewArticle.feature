Feature: View Wikipedia Article

Scenario: Search for and View HTML Article
  Given I navigate to "https://www.wikipedia.org/"
  When I search for "HTML"
  And I click on the "HTML" link
  Then I should see the "HTML" article page

Scenario Outline: Invalid Search
  Given I navigate to "https://www.wikipedia.org/"
  When I search for "<term>"
  Then I should see "<message>"

Examples:
  | term | message                                   |
  |      | Please enter some search terms            |
  | 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890 | Your search did not match any articles |