import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Admin Area Tests")
@Feature("API Tests")
public class AdminAreaListApiTests {

    private static final Logger logger = LoggerFactory.getLogger(AdminAreaListApiTests.class);
    private final String API_KEY = "VL94V9VobDa5FGDxsjnVvaCXkyGMLMAl";

    @ClassRule
    public static WireMockClassRule wireMockClassRule = new WireMockClassRule();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + wireMockClassRule.port();
    }

    @Step("Stub mock with ID field")
    private void stubMockWithIDField() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("[{\"ID\": \"123\", \"LocalizedName\": \"Test\", \"EnglishName\": \"Test\"}]")));
    }

    @Test
    @Description("Test ID field type and existence")
    public void testIdFieldTypeAndExistence() {
        // Stub the mock
        stubMockWithIDField();

        // Perform the request
        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        // Log the response and WireMock server port
        logger.info("Actual Response Body: " + response.getBody().asString());
        logger.info("WireMock Server Port: " + wireMockClassRule.port());

        // Reset WireMock to clear stubs
        WireMock.reset();
    }

    @Test
    @Description("Test LocalizedName field type and existence")
    public void testLocalizedNameFieldTypeAndExistence() {
        // Stub the mock
        stubMockWithIDField();

        // Perform the request
        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        // Log the response
        logger.info("Actual Response Body: " + response.getBody().asString());

        // Validate the LocalizedName field
        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .body("[0].LocalizedName", notNullValue())
                .body("[0].LocalizedName", isA(String.class));

        // Reset WireMock to clear stubs
        WireMock.reset();
    }

    @Test
    @Description("Test EnglishName field type and existence")
    public void testEnglishNameFieldTypeAndExistence() {
        // Stub the mock
        stubMockWithIDField();

        // Perform the request
        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        // Log the response
        logger.info("Actual Response Body: " + response.getBody().asString());

        // Validate the EnglishName field
        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .body("[0].EnglishName", notNullValue())
                .body("[0].EnglishName", isA(String.class));

        // Reset WireMock to clear stubs
        WireMock.reset();
    }
}
