Feature: SauceDemo Login

  Scenario: Valid login
    Given user is on login page
    When user enters username "standard_user" and password "secret_sauce"
    Then user should see the inventory page

  Scenario: Invalid login
    Given user is on login page
    When user enters username "standard_user" and password "wrong"
    Then error message should be displayed