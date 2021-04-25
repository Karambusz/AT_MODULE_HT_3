Feature: Smoke test
  Scenario: Check navigator items
    Given customer is on home page
    Then check that navigator item is present
      |navigator item name        |
      |navigator.item.1           |
      |navigator.item.2           |
      |navigator.item.3           |
      |navigator.item.4           |
      |navigator.item.5           |
      |navigator.item.6           |
      |navigator.item.7           |
      |navigator.item.8           |

  Scenario Outline: Check search field
    Given customer is on home page
    When customer performs search for '<search_term>'
    Then check text '<search_expected_text>' in department field

    Examples:
      | search_term       | search_expected_text                |
      | term.to.search.1  | search.result.department.1          |

  Scenario Outline: Sign In with incorrect email
    Given customer is on home page
    And redirect to sign in page
    And try to sign in with incorret email '<test_email>'
    Then check if warning text is appeared

    Examples:
      | test_email              |
      | fake.email.to.sign.in   |

  Scenario Outline: Confirmation code will be sent after you create your account
    Given customer is on home page
    And redirect to sign in page
    And fill registration data
    Then check if verification message '<verification_message>' will be shown

    Examples:
    |verification_message       |
    |email.verification.message |

  Scenario Outline: Check country and currency
    Given customer is on home page
    And check country of delivery '<expected_country>'
    And check that currency button is displayed

    Examples:
      | expected_country            |
      | country.of.delivery         |

  Scenario Outline: Check customer name after authorization
    Given customer is on home page
    And redirect to sign in page
    And fill email
    And fill password
    Then check if customer title is correct '<expected_title>'

    Examples:
      | expected_title            |
      | customer.expected.title   |


