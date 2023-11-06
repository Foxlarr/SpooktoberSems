
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class AdminAreaListApiTests {

    private static final Logger logger = LoggerFactory.getLogger(AdminAreaListApiTests.class);
    private final String API_KEY = "VL94V9VobDa5FGDxsjnVvaCXkyGMLMAl";

    // Инициализируем WireMock перед тестами
    @ClassRule
    public static WireMockClassRule wireMockClassRule = new WireMockClassRule();



    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + wireMockClassRule.port();
    }


    @Test
    public void testIdFieldTypeAndExistence() {
        // Создание мока для запроса, который вернет JSON с полем "ID"
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("[{\"ID\": \"123\", \"LocalizedName\": \"Test\", \"EnglishName\": \"Test\"}]")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        response.then()
                .body("[0].ID", notNullValue())
                .body("[0].ID", isA(String.class));
        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .body("[0].ID", notNullValue())
                .body("[0].ID", isA(String.class));

        WireMock.reset();
    }

    @Test
    public void testLocalizedNameFieldTypeAndExistence() {
        // Создание мока для запроса, который вернет JSON с полем "LocalizedName"
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("[{\"ID\": \"123\", \"LocalizedName\": \"Test\", \"EnglishName\": \"Test\"}]")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .body("[0].LocalizedName", notNullValue())
                .body("[0].LocalizedName", isA(String.class));

        WireMock.reset();
    }

    @Test
    public void testEnglishNameFieldTypeAndExistence() {
        // Создание мока для запроса, который вернет JSON с полем "EnglishName"
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("[{\"ID\": \"123\", \"LocalizedName\": \"Test\", \"EnglishName\": \"Test\"}]")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .body("[0].EnglishName", notNullValue())
                .body("[0].EnglishName", isA(String.class));

        WireMock.reset();
    }

    @Test
    public void testUnauthorizedError() {
        // Создание мока для запроса с неверным API ключом
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo("UnauthorizedAPIKey"))
                .willReturn(WireMock.aResponse()
                        .withStatus(401)
                        .withBody("{\"ErrorDescription\": \"Unauthorized. API authorization failed\"}")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", "UnauthorizedAPIKey")
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .statusCode(401)
                .body("ErrorDescription", equalTo("Unauthorized. API authorization failed"));

        WireMock.reset();
    }

    @Test
    public void testForbiddenError() {
        // Создание мока для запроса с неверным API ключом
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo("ForbiddenAPIKey"))
                .willReturn(WireMock.aResponse()
                        .withStatus(403)
                        .withBody("{\"ErrorDescription\": \"Unauthorized. You do not have permission to access this endpoint\"}")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", "ForbiddenAPIKey")
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .statusCode(403)
                .body("ErrorDescription", equalTo("Unauthorized. You do not have permission to access this endpoint"));

        WireMock.reset();
    }

    @Test
    public void testNotFoundError() {
        // Создание мока для запроса к несуществующему ресурсу
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/nonexistentarea"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(404)
                        .withBody("{\"ErrorDescription\": \"Server has not found a route matching the given URI\"}")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/nonexistentarea")
                .then()
                .statusCode(404)
                .body("ErrorDescription", equalTo("Server has not found a route matching the given URI"));

        WireMock.reset();
    }

    @Test
    public void testServerError() {
        // Создание мока для запроса, который вернет серверную ошибку (500)
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(500)
                        .withBody("{\"ErrorDescription\": \"Server encountered an unexpected condition which prevented it from fulfilling the request\"}")));

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .statusCode(500)
                .body("ErrorDescription", equalTo("Server encountered an unexpected condition which prevented it from fulfilling the request"));

        WireMock.reset();
    }

    @Test
    public void testResponseTime() {
        // Создание мока для запроса с задержкой
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/locations/v1/adminareas/AE"))
                .withQueryParam("apikey", WireMock.equalTo(API_KEY))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withFixedDelay(2000))); // Задержка 2000 мс

        Response response = given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE");

        logger.info("Actual Response Body: " + response.getBody().asString());

        given()
                .param("apikey", API_KEY)
                .when()
                .get("/locations/v1/adminareas/AE")
                .then()
                .time(lessThan(1000L)); // Проверка, что время ответа меньше 1000 мс

        WireMock.reset();
    }


}
