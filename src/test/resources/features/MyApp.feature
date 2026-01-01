Feature: Login functionality
  Scenario: User logins with valid credentials
    Given user is on login page
    When user gives correct username and correct password
    And user clicks on login button
    Then user lands on dashboard page