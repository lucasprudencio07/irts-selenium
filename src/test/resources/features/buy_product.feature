Feature: Buy Product
  As a user logged in in the page
  I want to choose a product on the home screen
  And see it on the shopping cart
  So I can finish my order

  @this
  Scenario: Should show a list with 8 products in the home screen
    Given I am in the home screen
    When I am not logged in
    Then I can see all the 8 products available to buy
    And the shopping cart must be empty

  @this
  Scenario: Should show chosen product confirmed
    Given I am in the home screen
    When I am logged in
    And I selected a product at index 0
    And the product name on the home screen is "Hummingbird Printed T-Shirt"
    And the product price on the home screen is "$19.12"
    And I added the product on the shopping cart with a "M" size with a "Black" color and 2 itens
    Then the product will show on the confirmation page as "Hummingbird Printed T-Shirt" costing "$19.12" at a "M" size with a "Black" color and 2 itens