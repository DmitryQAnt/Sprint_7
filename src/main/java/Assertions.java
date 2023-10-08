import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class Assertions {
    @Step("Assert is new courier is created successfully, code 201")
    public void assertCreatedSuccess(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");
    }

    @Step("Assert is  courier is login in successfully, code 200")
    public void loggedInSuccesfuly(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    @Step("Assert is  impossible to registrate two similar couriers, code 409")
    public void impossibleToRegistrateTwoSimilarCouriers(ValidatableResponse response1) {
        response1
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Assert is impossible to create new courier without password field, code 400")
    public void assertCreatedFail(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Step("Assert is possible to create order with any colors, code 201")
    public void orderRecieved(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("track", notNullValue());
    }

    @Step("Assert list of orders exist, code 200")
    public void responseContainListOfOrders(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body(notNullValue());
    }

    @Step("Assert is impossible to registrate courier with wrong login, code 404")
    public void impossibleRegistrationCourierWithWrongLogin(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Assert is impossible to registrate courier without login field, code 404")
    public void impossibleCourierLoginWithoutLoginField(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
