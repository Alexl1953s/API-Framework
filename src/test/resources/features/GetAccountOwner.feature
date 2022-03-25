Feature: Get a specific accounts owner

#  Background: get valid authentication bearer token
#    Given the admin user is authenticated
    @smoke
  Scenario: Get existing checking account's owner
    When User looks up for the owner of '206' account
    Then response status code should be '200'
    Then the following should be the response payload of GetOwner endpoint
      | id | username       | enabled | accountNonExpired | accountNonLocked | credentialsNonExpired |
      | 75 | nsmith@demo.io | true    | true              | true             | true                  |
    And the following should be userProfile response
      | id | firstName | lastName | title | gender | ssn         | dob        | dom        | emailAddress   | homePhone    | mobilePhone  | workPhone    | address        | locality         | region | postalCode | country |
      | 76 | Nicole    | Smith    | Mrs.  | F      | 807-65-1926 | 02/13/1988 | 09/24/2021 | nsmith@demo.io | 842-462-8191 | 958-571-0065 | 230-584-2033 | 644 Sammy Pass | East Sheltonland | MI     | 52632      | US      |






