# Rest-Assured API Testing Framework

This is a Java-based REST API testing framework using Rest-Assured. It includes sample test cases for the Foodics API.

## Prerequisites

Before running the tests, ensure you have the following installed:

- Java Development Kit (JDK)
- Maven

## Project Structure

The project is organized as follows:

- `src/main/java`: Contains the main source code.
  - `TestSetup.java`: Configuration class to set the base URL.
- `src/test/java`: Contains test classes.
  - `APITestCases.java`: Test cases for the Foodics API.
  - `TestData.java`: Test data class containing endpoint URLs and credentials.
- `pom.xml`: Maven project configuration file.

## Running the Tests

1. Clone this repository to your local machine.
2. Navigate to the project's root directory.
3. Open a terminal/command prompt.
4. Run the following command to execute the tests:


This command will trigger the execution of TestNG tests.

## Test Cases

### 1. Positive Login Test

- Description: Tests a successful login using valid credentials.
- Test Method: `testLoginEndpoint()`
- Expected Result: Expects a `200 OK` response and validates the token.

### 2. Positive Who Am I Test

- Description: Tests the "Who Am I" endpoint after a successful login.
- Test Method: `testWhoAmIEndpoint()`
- Expected Result: Expects a `200 OK` response and validates the user's email.

### 3. Negative Login Test (Bad Password)

- Description: Tests a failed login using an incorrect password.
- Test Method: `testBadLoginScenario()`
- Expected Result: Expects a `401 Unauthorized` response.


