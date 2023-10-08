import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    public static RequestSpecification getRequestSpecification() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}
