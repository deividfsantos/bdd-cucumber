Feature: FindOneBook

    Scenario: Find a specific book by the name

    Given a running application.

    When I access the endpoint "/v1/books/Watchmen" by GET

    Then it should return that the book has "650" pages

    And should return that the book is of the genre of "Comic Book" and the author is "Alan Moore"