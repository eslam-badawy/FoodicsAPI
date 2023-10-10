# Rest-Assured API Testing Framework

This is a Java-based REST API testing framework using Rest-Assured and BDD. It includes sample test cases for the Foodics API.

## Prerequisites

Before running the tests, ensure you have the following installed:

- Java Development Kit (JDK)
- Maven

## Project Structure

The project is organized as follows:

- `src/test/java`: Contains test classes.
  - `resources`: Test data.
     - `test-config.properties`: all the needed test data.
  - `test`: Test cases for the Foodics API.
     - `APITestCases.java`: Contains all test cases.
     - `TestDataLoading.java`: All methods to get the test data from test-config file.
- `pom.xml`: Maven project configuration file.
- `testng.xml`: Contains the class needed to be run.


## Running the Tests

1. Clone this repository to your local machine.
2. Navigate to the project's root directory.
3. Open a terminal/command prompt.
4. Run the following command to execute the tests:
 - mvn clean
 - mvn compile
 - mvn test -Dtest=APITestCases 

## Test Cases

### 1. Positive Login Test

- Description: Tests a successful login using valid credentials.
- Test Method: `testLoginEndpoint()`
- Expected Result: Expects a `200 OK` response and validates the token.

### 2. Positive Who Am I Test

- Description: Tests the "Who Am I" endpoint after a successful login.
- Test Method: `testWhoAmIEndpoint()`
- Expected Result: Expects a `200 OK` response and validates the user's email.

### 3. Negative Login Test (Bad email)

- Description: Tests a failed login using an incorrect password.
- Test Method: `testBadLoginScenario()`
- Expected Result: Expects a `500 internal server error` response.


