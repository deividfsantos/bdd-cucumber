Feature: RentABook

    Scenario: Rent a book and change the stock

        Given I want to rent the book with code 1 that has 5 in stock

        And I'm the client with the code 2

        When I rent the book by POST

        Then the book should be rented

        And have reduced 1 of your stock to stay with 4
