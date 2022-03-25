@functional @regression
Feature: CRUD Cheching account for specific user.

 ###Background is used for
  ##Given - Preconditions for our tests.
  ##When - Action
  ##Then - Validation


  #Example table which with Scenario Outline:
  #Data Table that goes under a specific step
  #goal of cucumber is to write extemely generic steps which can be reused for multiple scenarios with no code change

#  Background: get valid authentication bearer token
#    Given the admin user is authenticated

  Scenario: I should be able to create a new individual standard checking account with valid intro deposit
    When I send a the following create account payload
      | accountName       | accountTypeCode | openingDeposit | ownerTypeCode |
      | Lebron James Test | SCK             | 25             | IND           |
    Then response status code should be '200'
    And  the following should be the response payload
      | id  | name              | accountNumber | currentBalance | openingBalance |
      | 152 | Lebron James Test | 486130153     | 25.0           | 25.0           |
    And the following should be the accountType response payload
      | id | code | category | name              | minDeposit | overdraftLimit | overdraftFee |
      | 8  | SCK  | CHK      | Standard Checking | 25.0       | 25.0           | 10.0         |
    And the following should be the ownershipType response payload
      | id | code | name       |
      | 17 | IND  | Individual |
    And the following should be the accountStanding response payload
      | id | code | name |
      | 19 | A1   | Open |




#
#  Scenario: I should be able to update account
#    When I send a valid update account payload
#    Then response status code should be 201
#    And update account response should be valid
#
#  Scenario: I should be able to read account page
#    Then response status code should be 200
#    And get account response should be valid
#
#  Scenario: I should be able to delete account
#    When I send a valid delete account payload
#    Then response status code should be 202
#    And delete account response should be valid