Feature: Buy Product
  As a user logged in in the page
  I want to choose a product on the home screen
  And see it on the shopping cart
  So I can finish my order


  Scenario: Should show a list with 8 products in the home screen
    Given I am in the home screen
    When I am not logged in
    Then I can see all the 8 products available to buy
    And the shopping cart must be empty


  @this
  Scenario Template: Show chosen product
    Given I am in the home screen
    When I am logged in
    And I selected a product at index <index>
    And the product name on the home screen and on the product page is <productName>
    And the product price on the home screen and on the product page is <productPrice>
    And I added the product on the shopping cart with a <productSize> size with a <productColor> color and <productQuantity> itens
    Then the product will show on the confirmation page as <productName> costing <productPrice> at a <productSize> size with a <productColor> color and <productQuantity> itens

    Examples:
    |index  |         productName           |productPrice | productSize | productColor  | productQuantity |
    | 0     | "Hummingbird Printed T-Shirt" |  "$19.12"   | "M"         | "Black"       | 2               |
    | 1     | "Hummingbird Printed Sweater" |  "$28.72"   | "XL"        | "N/A"         | 3               |