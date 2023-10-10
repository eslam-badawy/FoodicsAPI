package test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class APITestCases {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TestDataLoading.getBaseURI();
    }

    @Test
    public void testLoginEndpoint() {
        // Prepare the request body
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
    }

    @Test
    public void testWhoAmIEndpoint() {
        // Use the token for authentication
        String token = TestDataLoading.getToken();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/whoami")
                .then()
                .extract()
                .response();

        // Assert the response status code is 200
        int expectedStatusCode = 200;
        assertEquals(response.getStatusCode(), expectedStatusCode, "Response status code doesn't match the expected value.");
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
