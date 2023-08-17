Feature: Test OrderController

  Background:
    Given The next user exists
      | username         | password  | isActive | roleList | firstName | lastName | email                     | city    | address        |
      | eduard.mititiuc1 | Test12345 | 1        | MEMBER   | Eduard    | Mititiuc | eduard.mititiuc@gmail.com | Singera | Alecu Russo 2A |
    And The next produces exist
      | name    | price | ingredients      | weight   | image    |
      | pizza   | 15    | some ingredients | 400      | img1.png |
      | lasagna | 15    | some ingredients | 380      | img2.png |
    And The user makes an order with both produces

  Scenario: Test get all orders
    When All orders are retrieved from the database
    Then There should be 1 order
    And The order should have 2 produces
    When The first order's id is used
    Then It should return the first order
    When User's id is used
    Then Its orders should be returned
    And These orders should be the one defined above
    And All data should be deleted from the data base

