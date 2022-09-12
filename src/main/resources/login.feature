Feature: Login Feature File

  @Login
  Scenario: Login scenario test for hudl website correct credentials
    Given user navigate to hudl website
    When user enters the valid username and password
    Then user should be successfully logged in to the application
    Then i close the browser

    @InvalidLogin @Test
    Scenario: Invalid Login test for hudl website incorrect credentials
      Given user navigate to hudl website
      When user enters the invalid username and password
      Then user should not be logged into the application
      Then i close the browser


      @LoginWithanOrganizationTestInvalid
      Scenario: Invalid login using an organization on hudl website
        Given user navigate to hudl website
        When user selects login with organization but with invalid email
        Then user is prompted to login with email and password
        Then i close the browser
