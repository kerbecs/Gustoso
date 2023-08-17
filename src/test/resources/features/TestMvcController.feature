Feature: Test Mvc Controller

  Scenario Outline: Get index page
    When User uses get <endpoint> endpoint with no parameter
    Then It should receive <status> status
    And It should receive <page> page

    Examples:
      | endpoint      | status | page         |
      | /             | 200    | index        |
      | /aboutUs      | 200    | about        |
      | /registered   | 200    | registered   |
      | /noPermission | 200    | noPermission |
      | /error        | 200    | error        |

  Scenario: Test register user with correct credentials
    Given An user registers with the next credentials
      | username         | password  | isActive | roleList | firstName | lastName | email                     | city    | address        |
      | eduard.mititiuc1 | Test12345 | 1        | MEMBER   | Eduard    | Mititiuc | eduard.mititiuc@gmail.com | Singera | Alecu Russo 2A |
    Then It should receive 200 status
    And The user should be registered by receiving activated page
    And It should have MEMBER role by default
    And It should have inactive status
    And Inserted user should be deleted

  Scenario: Test register user with incorrect credentials
    Given An user registers with the next credentials
      | username         | password | isActive | roleList | firstName | lastName | email                     | city    | address        |
      | eduard.mititiuc1 | test     | 1        | MEMBER   | e         | m        | eduard.mititiuc@gmail.com | Singera | Alecu Russo 2A |
    And It should receive 200 status
    Then The user should be registered by receiving register page
    And The user should not be registered with success

