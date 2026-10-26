
Feature: SauceDemo Login Functionality

  Scenario: Login with valid credentials
    Given User is on SauceDemo login page
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    And User clicks login button
    Then User should see products page

  Scenario: Login with invalid password
    Given User is on SauceDemo login page
    When User enters username "standard_user"
    And User enters password "wrong123"
    And User clicks login button
    Then User should see login error message

  Scenario: Login with locked out user
    Given User is on SauceDemo login page
    When User enters username "locked_out_user"
    And User enters password "secret_sauce"
    And User clicks login button
    Then User should see login error message
