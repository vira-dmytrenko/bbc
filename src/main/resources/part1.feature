Feature: Task1
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check headline article
    Given user opens BBC home page '<homePage>'
    When user clicks on News link in navigation bar
    Then headline article title is '<expectedHeadlineTitle>'
    Examples:
      | homePage        | expectedHeadlineTitle                          |
      | https://bbc.com | Ukraine claims fight back in key eastern city  |


  Scenario Outline: Check secondary article headlines
    Given user opens BBC home page '<homePage>'
    When user clicks on News link in navigation bar
    Then secondary article titles are <expectedTitles>
    Examples:
      | homePage        | expectedTitles                                                                                                                                                                                                                            |
      | https://bbc.com | Meet the baby as old as the war; Shot dead by police while trying to get fuel; Iran's Khamenei accuses 'enemy' of stirring protests; Royals lead Jubilee events as Queen misses Derby; Mariah Carey sued for copyright over Christmas hit |



  Scenario Outline: Check  by headline title's category name
    Given user opens BBC home page '<homePage>'
    And user clicks on News link in navigation bar
    When user remembers headline article search category as 'cat'
    And user searches value saved as 'cat'
    Then search results contain title '<expectedTitle>' at position 1
    Examples:
      | homePage        | expectedTitle                           |
      | https://bbc.com | Risk and Reward: Europe\'s China Gamble |
