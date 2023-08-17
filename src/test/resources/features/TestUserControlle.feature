Feature: Test UserController

  Background:
    Given The next users exist
      | username         | password  | isActive | roleList | firstName | lastName | email                     | city     | address        |
      | eduard.mititiuc1 | Test12345 | 1        | MEMBER   | Eduard    | Mititiuc | eduard.mititiuc@gmail.com | Singera  | Alecu Russo 2A |
      | alex.goncear     | Test12345 | 1        | MEMBER   | Alex      | Goncear  | alex.goncear@gmail.com    | Chisinau | Viilor 5 A     |

  Scenario: Retrieve all users
    When All users are retrieved from the database
    Then All users from above should be returned
    When User with username 'alex.goncear' is retrieved
    Then This user should be returned
    And Delete all inserted data

