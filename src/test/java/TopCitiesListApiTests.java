import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TopCitiesListApiTests {
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
                .get("/locations/v1/topcities/50")
                .then()
                .statusCode(200);
    }

    @Test
    public void testVersionField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Version", isA(Integer.class));
    }

    @Test
    public void testKeyField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Key", isA(String.class));
    }

    @Test
    public void testTypeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Type", isA(String.class));
    }

    @Test
    public void testRankField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Rank", isA(Integer.class));
    }

    @Test
    public void testLocalizedNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].LocalizedName", isA(String.class));
    }

    @Test
    public void testEnglishNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].EnglishName", isA(String.class));
    }

    @Test
    public void testPrimaryPostalCodeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].PrimaryPostalCode", isA(String.class));
    }

    @Test
    public void testRegionIdField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Region.ID", isA(String.class));
    }

    @Test
    public void testRegionLocalizedNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Region.LocalizedName", isA(String.class));
    }

    @Test
    public void testCountryIdField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Country.ID", isA(String.class));
    }

    @Test
    public void testCountryLocalizedNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].Country.LocalizedName", isA(String.class));
    }

    @Test
    public void testAdministrativeAreaIdField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].AdministrativeArea.ID", isA(String.class));
    }

    @Test
    public void testAdministrativeAreaLocalizedNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].AdministrativeArea.LocalizedName", isA(String.class));
    }

    @Test
    public void testAdministrativeAreaLevelField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].AdministrativeArea.Level", isA(Integer.class));
    }

    @Test
    public void testAdministrativeAreaLocalizedTypeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].AdministrativeArea.LocalizedType", isA(String.class));
    }

    @Test
    public void testAdministrativeAreaEnglishTypeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].AdministrativeArea.EnglishType", isA(String.class));
    }

    @Test
    public void testAdministrativeAreaCountryIdField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].AdministrativeArea.CountryID", isA(String.class));
    }

    @Test
    public void testTimeZoneCodeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].TimeZone.Code", isA(String.class));
    }

    @Test
    public void testTimeZoneNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].TimeZone.Name", isA(String.class));
    }

    @Test
    public void testTimeZoneGmtOffsetField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].TimeZone.GmtOffset", isA(Integer.class));
    }

    @Test
    public void testTimeZoneIsDaylightSavingField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].TimeZone.IsDaylightSaving", isA(Boolean.class));
    }

    @Test
    public void testTimeZoneNextOffsetChangeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].TimeZone.NextOffsetChange", anyOf(nullValue(), isA(String.class)));
    }

    @Test
    public void testGeoPositionLatitudeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Latitude", isA(Double.class));
    }

    @Test
    public void testGeoPositionLongitudeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Longitude", isA(Double.class));
    }

    @Test
    public void testGeoPositionElevationMetricValueField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Elevation.Metric.Value", isA(Double.class));
    }

    @Test
    public void testGeoPositionElevationMetricUnitField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Elevation.Metric.Unit", isA(String.class));
    }

    @Test
    public void testGeoPositionElevationMetricUnitTypeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Elevation.Metric.UnitType", isA(Integer.class));
    }

    @Test
    public void testGeoPositionElevationImperialValueField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Elevation.Imperial.Value", isA(Double.class));
    }

    @Test
    public void testGeoPositionElevationImperialUnitField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Elevation.Imperial.Unit", isA(String.class));
    }

    @Test
    public void testGeoPositionElevationImperialUnitTypeField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].GeoPosition.Elevation.Imperial.UnitType", isA(Integer.class));
    }

    @Test
    public void testIsAliasField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].IsAlias", isA(Boolean.class));
    }

    @Test
    public void testParentCityKeyField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].ParentCity.Key", anyOf(nullValue(), isA(String.class)));
    }

    @Test
    public void testParentCityLocalizedNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].ParentCity.LocalizedName", anyOf(nullValue(), isA(String.class)));
    }

    @Test
    public void testParentCityEnglishNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].ParentCity.EnglishName", anyOf(nullValue(), isA(String.class)));
    }

    @Test
    public void testSupplementalAdminAreasLevelField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].SupplementalAdminAreas[0].Level", isA(Integer.class));
    }

    @Test
    public void testSupplementalAdminAreasLocalizedNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].SupplementalAdminAreas[0].LocalizedName", isA(String.class));
    }

    @Test
    public void testSupplementalAdminAreasEnglishNameField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].SupplementalAdminAreas[0].EnglishName", isA(String.class));
    }

    @Test
    public void testDataSetsField() {
        given()
                .param("apikey", "API_KEY")
                .when()
                .get("/locations/v1/topcities/50")
                .then()
                .body("[0].DataSets", isA(List.class));
    }
}
