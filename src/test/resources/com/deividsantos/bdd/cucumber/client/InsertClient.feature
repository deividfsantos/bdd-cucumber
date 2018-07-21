Feature: InsertOneClient

    Scenario: Insert a client into the database

        Given I want to save the client "Joao silveira" with the phone "51985652458"

        And nationality is "BR"

        When I access the endpoint "/v1/clients" by POST with this client

        Then should save the client

        And return 200 with this client with nationality "Brazil"