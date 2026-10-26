
Feature: SauceDemo Checkout Functionality

  Scenario: Complete checkout successfully
    Given User is on SauceDemo login page
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    And User clicks login button
    And User adds Backpack to cart
    And User adds Bike Light to cart
    And User opens shopping cart
    And User clicks checkout button
    And User enters checkout first name "Najiya"
    And User enters checkout last name "Maner"
    And User enters checkout postal code "411033"
    And User clicks continue button
    And User clicks finish button
    Then User should see order confirmation
