import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RegionListApiTests {
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
                .get("/locations/v1/regions")
                .then()
                .statusCode(200);
    }

    @Test
    public void testResponseIsArray() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/regions")
                .then()
                .body("", isA(List.class)) // Проверка, что ответ - это массив
                .body("", hasSize(10)); // Массив должен содержать 10 объектов
    }

    @Test
    public void testEachObjectInResponseArray() {
        List<String> idList = given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/regions")
                .then()
                .extract()
                .path("ID");

        idList.forEach(id -> {
            given()
                    .param("apikey", "API_KEY")
                    .when()
                    .get("/locations/v1/regions/" + id)
                    .then()
                    .body("ID", is(id))
                    .body("ID", isA(String.class)) // Проверка, что ID является строкой
                    .body("LocalizedName", isA(String.class)) // Проверка, что LocalizedName является строкой
                    .body("EnglishName", isA(String.class)); // Проверка, что EnglishName является строкой
        });
    }

    @Test
    public void testRequestSyntaxOrInvalidParameterError() {
        given()
                .param("apikey", "InvalidAPIKey") //неверный API ключ для генерации ошибки
                .when()
                .get("/locations/v1/regions")
                .then()
                .statusCode(400)
                .body("ErrorDescription", equalTo("Request had bad syntax or the parameters supplied were invalid"));
    }

    @Test
    public void testUnauthorizedError() {
        given()
                .param("apikey", "UnauthorizedAPIKey") // неверный API ключ для генерации ошибки
                .when()
                .get("/locations/v1/regions")
                .then()
                .statusCode(401)
                .body("ErrorDescription", equalTo("Unauthorized. API authorization failed"));
    }

    @Test
    public void testForbiddenError() {
        given()
                .param("apikey", "ForbiddenAPIKey") //  неверный API ключ для генерации ошибки
                .when()
                .get("/locations/v1/regions")
                .then()
                .statusCode(403)
                .body("ErrorDescription", equalTo("Unauthorized. You do not have permission to access this endpoint"));
    }

    @Test
    public void testNotFoundError() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/nonexistentregion")
                .then()
                .statusCode(404)
                .body("ErrorDescription", equalTo("Server has not found a route matching the given URI"));
    }


    @Test
    public void testResponseTime() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/regions")
                .then()
                .time(lessThan(1000L)); // Проверка, что время ответа меньше 1000 мс
    }
}
