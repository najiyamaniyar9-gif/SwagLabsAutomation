
Feature: SauceDemo Remove from Cart

  Scenario: Remove one product from cart
    Given User is on SauceDemo login page
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    And User clicks login button
    And User adds Backpack to cart
    And User adds Bike Light to cart
    And User adds Bolt T-Shirt to cart
    Then Cart should display 3 products
    When User removes Bike Light from cart
    Then Cart should display 2 products
