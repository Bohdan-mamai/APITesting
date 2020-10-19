Feature: test on asserts

  Scenario: Try to execute test
    Given user check that inventory not null
    When user place order and check that it was saved
    Then User delete order and check it
    And Try to delete imagine order