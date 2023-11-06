import io.restassured.RestAssured;

public class ApiTestConfig {
    private static final String API_KEY = "VL94V9VobDa5FGDxsjnVvaCXkyGMLMAl";

    public static void setup() {
        RestAssured.baseURI = "http://dataservice.accuweather.com";
        RestAssured.basePath = "/locations/v1";
        RestAssured.requestSpecification = RestAssured.given().param("apikey", API_KEY);
    }
}
