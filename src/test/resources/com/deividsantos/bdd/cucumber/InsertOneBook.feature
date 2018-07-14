Feature: InsertOneBook

    Scenario: Save a book properly

        Given I have the book "The Universe in a nutshell"

        And it has "456" pages, of the genre "Science" and author "Stephen Hawkings"

        When I save the book in the endpoint "/v1/books" by POST

        And search the book

        Then should return all data from the book "The Universe in a nutshell"
