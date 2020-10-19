Feature: test on response codes

  Scenario: Try to execute test
    Given User place an order with
    When User check that order with exists
    And User delete an order with
    Then User check that store inventory is not empty
    And Check that order was deleted