Feature: Add product to the cart
  Every anonymous customer has posibility to find and add product to the cart and then remove

  Scenario Outline: Succsessful addition
    Given anonymous customer is on home page
    When customer performs search for '<search_term>'
    And pick the product
    And add '<number_of_products>' product to the basket
    And open the basket Page
    And remove all products from basket
    Then check that '<number_of_products_after_removal>' lefts

    Examples:
      |search_term          |number_of_products |number_of_products_after_removal     |
      |item.to.add          |product.count      |product.count.after.remove           |