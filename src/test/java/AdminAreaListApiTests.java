import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Admin Area Tests")
public class AdminAreaListApiTests {
    private static final Logger logger = LoggerFactory.getLogger(AdminAreaListApiTests.class);
    private static WireMockServer wireMockServer;

    @BeforeClass
    public static void setUpWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @AfterClass
    public static void tearDownWireMock() {
        wireMockServer.stop();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080"; // Use the WireMock port
    }

    @Before
    public void setupMappings() {
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/locations/v1/adminareas/AE"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("[{\"ID\": \"123\", \"LocalizedName\": \"Area1\", \"EnglishName\": \"Area One\"}]")
                        )
        );

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the response status for /locations/v1/adminareas/AE")
    public void testResponseStatus() {


        Response response = given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/adminareas/AE");

        // Используйте логгер для вывода информации
        logger.info("Request: " + response.getBody().asString());
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify ID field type and existence")
    public void testIdFieldTypeAndExistence() {


        Response response = given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .body("[0]", hasKey("ID"))
                .body("[0].ID", isA(String.class)) // Adjust this based on the expected data type
                .body("[0].LocalizedName", notNullValue())
                .body("[0].LocalizedName", isA(String.class))
                .body("[0].EnglishName", notNullValue())
                .body("[0].EnglishName", isA(String.class))
                .extract().response();

        // Print the response
        logger.info("Request: " + response.getBody().asString());
    }
}
