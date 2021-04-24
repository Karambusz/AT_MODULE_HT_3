Feature: Add product to the cart
  Every anonymous customer has posibility to find and add product to the cart

  Scenario Outline: Succsessful addition
    Given anonymous customer is on home page
    When customer performs search for '<search_term>'
    And pick the product
    And add '<number_of_products>' product to the basket
    Then check that '<number_of_products>' product was added

    Examples:
      |search_term          |number_of_products |
      |term.to.search.1     | product.count     |