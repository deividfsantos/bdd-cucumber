Feature: FindOneBook

    Scenario Outline: Find a specific book by the name

        Given a running application

        When I access the endpoint "<endpoint>" by GET

        Then should return the book with the name "<name>" that has "<pages>" pages

        And should return that the book is of the genre of "<genre>" and the author is "<author>"

        Examples:
            | endpoint    | name                    | pages | genre      | author           |
            | /v1/books/1 | Harry Potter            | 256   | Fantasy    | J. K. Rowling    |
            | /v1/books/2 | A Brief History of Time | 385   | Science    | Stephen Hawkings |
            | /v1/books/3 | Watchmen                | 650   | Comic Book | Alan Moore"      |