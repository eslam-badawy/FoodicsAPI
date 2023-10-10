package test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APITestCases {

    private String authToken; // Variable to store the authentication token

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TestDataLoading.getBaseURI();
    }

    @Test
    public void testLoginEndpoint() {
        String requestBody = "{\n" +
                "\"email\": \"" + TestDataLoading.getEmail() + "\",\n" +
                "\"password\": \"" + TestDataLoading.getPassword() + "\",\n" +
                "\"token\": \"" + TestDataLoading.getToken() + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
            .when()
                .post("/login")
            .then()
                .extract()
                .response();

        // Assert the response status code is 200
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode, "Response status code doesn't match the expected value.");

        // Extract and store the token
        authToken = response.jsonPath().getString("token");
    }

    @Test(dependsOnMethods = "testLoginEndpoint")
    public void testWhoAmIEndpoint() {
        // Check if authToken is not null (assuming it's set in the login test)
        assertTrue(authToken != null, "Authentication token is null. Please ensure you've logged in.");

        // Define the request headers with the authToken
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken) // Add the token as a Bearer token
            .when()
                .get("/whoami")
            .then()
                .extract()
                .response();

        // Assert the response status code is 200
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode, "Response status code doesn't match the expected value.");

        // Assert the email in the response matches the login email
        String loginEmail = TestDataLoading.getEmail();
        String responseEmail = response.jsonPath().getString("user.email");
        assertEquals(responseEmail, loginEmail, "Email in response doesn't match the login email.");

        // You can add more assertions based on the response body, headers, etc.
    }


    @Test
    public void testBadLoginScenario() {
        // Prepare the request body
        String requestBody = "{\n" +
                "\"email\": \"" + TestDataLoading.getWrongEmail() + "\",\n" +
                "\"password\": \"" + TestDataLoading.getPassword() + "\",\n" +
                "\"token\": \"" + TestDataLoading.getToken() + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract()
                .response();

        // Assert the response status code is 500 (internal server error)
        int expectedStatusCode = 500;
        assertEquals(response.getStatusCode(), expectedStatusCode, "Response status code doesn't match the expected value.");
    }
}
