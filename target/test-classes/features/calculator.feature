Feature: Calculator
  I don't know how to make operations
  So I want to use the system calculator
  To obtains the correct results

  Scenario Template: : Sum of 2 numbers
    Given that I access the calculator
    When I sum the <value1> and <value2>
    Then the result of this sum should be <result>

    Examples:
      | value1 | value2 | result |
      | 10     | 10     | 20     |
      | 100    | 100    | 200    |
      | 1000   | 1000   | 2000   |
