Feature: Login functionality
  Scenario: User logs in with valid credentials
    Given user is on login page
    When user gives correct username and correct password
    And user clicks on login button
    Then user lands on dashboard page

  Scenario Template: User login
    Given user is on login page
    When user gives "<username>" and "<password>"
    And user clicks on login button
    Then user lands on dashboard page

    Examples:
      | username | password |
      | student2 | Password3 |
      | student1 | Password2 |
      | student | Password123 |