Feature: Registration

  Scenario Outline: succeful registration
    Given Pepito wants to sign up in the application
    When Pepito sends the  requeried inormation to sign up
      | <name> | <last name> | <age> | <country> | <email> |
    Then Pepito should have a new account created
    Examples:
      | name      | last name | age | country  | email                  |
      | Alexander | Sanchez   | 30  | Colombia | alexsanpa028@gmail.com |
      | David     | Alejandro | 29  | Colombia | davi818@gmail.com      |

  Scenario: Missing required fields for registration
    Given Pepito wants to sign up in the application
    When Pepito does not send the required information
    Then Pepito should be told all fields are required


