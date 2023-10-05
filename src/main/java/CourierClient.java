
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class CourierClient {
    public static final String COURIER_PATH = "/api/v1/courier";
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static int loggedInCourierId;

    @Step("Creation of courier account")
    public static ValidatableResponse create(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .and()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }
    @Step("Login in courier")
    public static ValidatableResponse login(Credentials creds) {
        return
                given().log().all()
                        .contentType(ContentType.JSON)
                        .baseUri(BASE_URI)
                        .and()
                        .body(creds)
                        .when()
                        .post(COURIER_PATH + "/login")
                        .then().log().all();
    }
    @Step("Extraction of courierId (need for delete them later)")
    public int getLoggedInCourierId(ValidatableResponse loginResponse) {
        int loggedInCourierId = loginResponse
                .extract()
                .path("id");
        return loggedInCourierId;
    }
    @Step("Deleting of courier")
    public ValidatableResponse delete(int loggedInCourierId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .and()
                .body(Map.of("id", loggedInCourierId))
                .when()
                .delete(COURIER_PATH + "/" + loggedInCourierId)
                .then().log().all();
    }
}
