
@tag
Feature: Error validations
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error message validation
    Given I landed on Ecommerce Page
    When Logged in with username <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | email         | password |
      | mon@gmail.com | ZAQ!wsx  | 