import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CountryApiTests {
    private final String API_KEY = "MvBCZak8ZGvA4H40NW5n0OBS660gsME7";
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://dataservice.accuweather.com";
    }

    @Test
    public void testResponseStatus() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .statusCode(200);
    }

    @Test
    public void testRequestSyntaxOrInvalidParameterError() {
        given()
                .param("apikey", "InvalidAPIKey") // Введите неверный API ключ для генерации ошибки
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .statusCode(400)
                .body("ErrorDescription", equalTo("Request had bad syntax or the parameters supplied were invalid"));
    }

    @Test
    public void testUnauthorizedError() {
        given()
                .param("apikey", "UnauthorizedAPIKey") // Введите неверный API ключ для генерации ошибки
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .statusCode(401)
                .body("ErrorDescription", equalTo("Unauthorized. API authorization failed"));
    }

    @Test
    public void testForbiddenError() {
        given()
                .param("apikey", "ForbiddenAPIKey") // Введите неверный API ключ для генерации ошибки
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .statusCode(403)
                .body("ErrorDescription", equalTo("Unauthorized. You do not have permission to access this endpoint"));
    }

    @Test
    public void testNotFoundError() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/nonexistentcountry")
                .then()
                .statusCode(404)
                .body("ErrorDescription", equalTo("Server has not found a route matching the given URI"));
    }

    @Test
    public void testServerError() {
        // В данном случае трудно сгенерировать серверную ошибку через тесты
        // Этот тест оставлен для информативных целей, и вам нужно будет проверить его на реальной ошибке, если таковая возникнет
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .statusCode(500)
                .body("ErrorDescription", equalTo("Server encountered an unexpected condition which prevented it from fulfilling the request"));
    }

    @Test
    public void testResponseTime() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .time(lessThan(1000L)); // Проверка, что время ответа меньше 1000 мс
    }

    @Test
    public void testIdFieldTypeAndExistence() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .body("[0].ID", notNullValue())
                .body("[0].ID", isA(String.class));
    }

    @Test
    public void testLocalizedNameFieldTypeAndExistence() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .body("[0].LocalizedName", notNullValue())
                .body("[0].LocalizedName", isA(String.class));
    }

    @Test
    public void testEnglishNameFieldTypeAndExistence() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/countries/MEA")
                .then()
                .body("[0].EnglishName", notNullValue())
                .body("[0].EnglishName", isA(String.class));
    }
}
