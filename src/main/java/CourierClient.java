import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class CourierClient extends BaseClient {
    public static final String COURIER_PATH = "/api/v1/courier";

    @Step("Creation of courier account")
    public static ValidatableResponse create(Courier courier) {
        return getRequestSpecification()
                .log().all()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Login in courier")
    public static ValidatableResponse login(Credentials creds) {
        return getRequestSpecification()
                .log().all()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then();
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
        return getRequestSpecification()
                .log().all()
                .body(Map.of("id", loggedInCourierId))
                .when()
                .delete(COURIER_PATH + "/" + loggedInCourierId)
                .then();
    }
}
