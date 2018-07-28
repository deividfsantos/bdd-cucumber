# bdd-cucumber

Microservice created using Behavior Driven Development with Cucumber, WireMock and H2

## Running the application
   Create the tables running the SQL files in src/main/resources/com.deividsantos.bdd/database/
   If you need, change the configuration in Application.properties
   
**Build the service with:**
    
    ./gradlew clean build

 Run the project with your IDE or with:
 
    java -jar build/libs/bdd-cucumber-1.0.jar


## Running Tests

If you need, change the configuration in Application.properties
    Run the tests with:

    ./gradlew clean test 

Check the features and the stepdefs files
