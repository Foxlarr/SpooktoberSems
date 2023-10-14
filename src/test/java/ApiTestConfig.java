import io.restassured.RestAssured;

public class ApiTestConfig {
    private static final String API_KEY = "MvBCZak8ZGvA4H40NW5n0OBS660gsME7";

    public static void setup() {
        RestAssured.baseURI = "http://dataservice.accuweather.com";
        RestAssured.basePath = "/locations/v1";
        RestAssured.requestSpecification = RestAssured.given().param("apikey", API_KEY);
    }
}
