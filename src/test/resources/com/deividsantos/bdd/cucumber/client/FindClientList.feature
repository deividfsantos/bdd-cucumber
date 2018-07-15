Feature: FindAllClients

    Scenario: Find a list of clients

        Given I want to find all saved clients

        When I access API "/v1/clients" with GET

        Then should return a list with all saved clients