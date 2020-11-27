@Show
Feature: ordering items from the basket

  Scenario Outline:
    Given user is in Home products "https://shop.lillydrogerie.bg/produkti-za-doma#"
    And add items to the basket
    And the user clicks the basket
    When and clicks checkout
    And user fill the necessary Data "<First Name>","<Last Name>","<Phone Number>","<City>","<email>" and "<ShippingAddress>"
    And pres submit button
    Then the user is redirected to the payment page
    Examples:
      | First Name | Last Name | Phone Number | City  | email      | ShippingAddress |
      | Dimitar    | Kirov     | 0987654534   | Sofia | abv@abv.bg | Jerusalim       |

