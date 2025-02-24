Feature: Lending Books
  Description: The administrator registers books lend to users
  Actors: Administrato

  Scenario: Overdue book after 28 days
    Given the user has borrowed a book
    And 29 days have passed
    And the fine for one overdue book is 100 DKK
    Then the user has overdue books
    And the user has to pay a fine of 100 DKK

  Scenario: Guy returns book on time
    Given the user has borrowed a book
    And 26 days have passed
    And the fine for one overdue book is 100 DKK
    When the user returns the book
    Then the user does not have overdue books
    And the user has to pay a fine of 0 DKK

  Scenario: Guy returns book on time and 4 days pass
    Given the user has borrowed a book
    And 26 days have passed
    And the fine for one overdue book is 100 DKK
    When the user returns the book
    And 4 days have passed
    Then the user does not have overdue books
    And the user has to pay a fine of 0 DKK

  Scenario: Overdue book after 29 days and 4 days pass
    Given the user has borrowed a book
    And 29 days have passed
    And the fine for one overdue book is 100 DKK
    And the user has to pay a fine of 100 DKK
    When the user returns the book
    And 4 days have passed
    Then the user does not have overdue books
    And the user has to pay a fine of 0 DKK

