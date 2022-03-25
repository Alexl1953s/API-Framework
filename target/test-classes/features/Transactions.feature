Feature: Transactions

@regression
  Scenario: I should be able to send a debit transaction with my checking account
    When I send the following new transaction payload with the checking account id '206'
      | amount | description    | transactionTypeCode |
      | 12     | Starbucks      | DBT                 |
    Then response status code should be '200'
    Then the following should be the transaction response payload
      | description | amount |
      | Starbucks   | -12.0  |
    Then the following should be the transaction type response payload
      | code | name  | category |
      | DBT  | Debit | DEBIT    |
    Then the following should be the transaction state response payload
      | code | name     |
      | COM  | Complete |
    Then the following should be the transaction category response payload
      | name | code |
      | Misc | MIS  |