Feature: See Products on the Home Screen
  As a user without login in the page
  I want to visualize all the products on the home screen
  So I can choose what I want to buy

  @this
  Scenario: Should show a list with 8 products in the home screen
    Given I am in the home screen
    When I am not logged in
    Then I can see all the 8 products available to buy
    And the shopping cart must be empty