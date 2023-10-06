import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import junit.extensions.TestSetup;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;




public class APITestCases {

    @BeforeClass
    public void setUp() {
        TestSetup.configure();
    }

    @Test
    public void testLoginEndpoint() {
        given()
            .contentType("application/json")
            .body("{ \"email\": \"" + TestData.EMAIL + "\", \"password\": \"" + TestData.PASSWORD + "\" }")
        .when()
            .post(TestData.LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .body("token", equalTo(TestData.TOKEN));
    }

    @Test
    public void testWhoAmIEndpoint() {
        given()
            .header("Authorization", "Bearer " + TestData.TOKEN)
        .when()
            .get(TestData.WHO_AM_I_ENDPOINT)
        .then()
            .statusCode(200)
            .body("email", equalTo(TestData.EMAIL));
    }

    @Test
    public void testBadLoginScenario() {
    // Test Scenario: Bad Login (Incorrect Password)
    given()
        .contentType("application/json")
        .body("{ \"email\": \"" + TestData.EMAIL + "\", \"password\": \"incorrectpassword\" }")
    .when()
        .post(TestData.LOGIN_ENDPOINT)
    .then()
        .statusCode(401); // Expecting a 401 Unauthorized response
}

}
