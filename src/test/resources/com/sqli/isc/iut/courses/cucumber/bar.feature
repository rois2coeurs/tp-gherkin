Feature: Manage Bar Occupancy and Orders

  Scenario: Bar is full, deny entry
    Given there are already 9 people in the bar
    And the group is composed of
      | Mr Pignon  |
      | Mr Leblanc |
    When they try to enter the bar
    Then they should be denied entry
#    And the bar should display "Full" : /!\ Le bar n'est pas plein, il y a 9 personnes dedans... /!\

  Scenario: Bar has 8 people, last entry makes it full
    Given there are already 8 people in the bar
    And the group is composed of
        | Mr Pignon  |
        | Mr Leblanc |
    When they try to enter the bar
    Then they should be allowed entry
    And the person behind them should be denied entry
    And the bar should display "Full"
    When they each order a cocktail of the month
    Then the bill should be 20 euros

  Scenario: Bar has 3 people, multiple orders
    Given there are already 3 people in the bar
    And the group is composed of
      | Mr Pignon  |
      | Mr Leblanc |
    When they try to enter the bar
    Then they should be allowed entry
    When they each order a cocktail of the month
    Then "Mr Pignon" bill should be 10 euros
    And "Mr Leblanc" bill should be 10 euros
    When "Mr Pignon" pays his bill
    Then "Mr Pignon" bill should be 0 euros
    When "Mr Leblanc" orders 2 more cocktails of the month
    Then "Mr Leblanc" bill should be 30 euros
    And "Mr Pignon" bill should be 0 euros
    When "Mr Leblanc" pays his bill
    Then "Mr Leblanc" bill should be 0 euros
