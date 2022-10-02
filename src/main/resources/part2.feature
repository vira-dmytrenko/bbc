Feature: Part2
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: user fill question form without question text
    Given user opens BBC home page '<homePage>'
    And user clicks on News link in navigation bar
    And user clicks on Coronavirus tab
    And user clicks on 'Your Coronavirus Stories' tab
    When user clicks on 'Your questions answered: What questions do you have?' link
    And user fills form
      | Name                                           |user     |
      | Email address                                  |email    |
    And user clicks 'Submit' button
    Then user checks '<error message>' appears in empty required '<field>'

    Examples:
      | homePage             | error message    | field                                       |
      | https://www.bbc.com/ | can\'t be blank  | What questions would you like us to answer? |


  Scenario Outline: user fill question form with empty Name field
    Given user opens BBC home page '<homePage>'
    And user clicks on News link in navigation bar
    And user clicks on Coronavirus tab
    And user clicks on 'Your Coronavirus Stories' tab
    When user clicks on 'Your questions answered: What questions do you have?' link
    And user fills form
      | What questions would you like us to answer?    | question   |
      | Email address                                  | email      |
    And user clicks 'Submit' button
    Then user checks '<error message>' appears in empty required '<field>'

    Examples:
      | homePage             | error message         | field  |
      | https://www.bbc.com/ | Name can\'t be blank  | Name   |


  Scenario Outline: user fill question form without email field
    Given user opens BBC home page '<homePage>'
    And user clicks on News link in navigation bar
    And user clicks on Coronavirus tab
    And user clicks on 'Your Coronavirus Stories' tab
    When user clicks on 'Your questions answered: What questions do you have?' link
    And user fills form
      | What questions would you like us to answer?    | X        |
      | Name                                           | user     |
    And user clicks 'Submit' button
    Then user checks '<error message>' appears in empty required '<field>'

    Examples:
      | homePage             | error message                  | field         |
      | https://www.bbc.com/ | Email address can\'t be blank  | Email address |


