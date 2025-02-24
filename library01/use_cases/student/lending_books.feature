Feature: Lending Books
  Description: The administrator registers books lend to users
  Actors: Administrato

  Scenario: Overdue book after 28 days
    Given the user has borrowed a book
    And 29 days have passed
    And the fine for one overdue book is 100 DKK
    Then the user has overdue books
    And the user has to pay a fine of 100 DKK