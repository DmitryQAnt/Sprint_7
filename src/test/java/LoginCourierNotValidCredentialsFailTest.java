import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginCourierNotValidCredentialsFailTest {
    private final CourierClient client = new CourierClient();
    private final Assertions check = new Assertions();
    protected int courierId;
    private Courier courierBasic;
    private Courier courierWithWrongPassword;

    @Test
    @DisplayName("Check impossible to create new courier  with  not valid password")
    @Description("Negative test for /api/v1/courier endpoint")
    public void checkCourierLoginWithWrongPassword() {
        courierBasic = CourierGenerator.CourierBasic();
        courierWithWrongPassword = CourierGenerator.CourierWrongPassword();
        CourierClient.create(courierBasic);
        Credentials basics = Credentials.from(courierBasic);
        ValidatableResponse basicLoginResponse = CourierClient.login(basics);
        courierId = client.getLoggedInCourierId(basicLoginResponse);
        Credentials credsWrong = Credentials.from(courierWithWrongPassword);
        ValidatableResponse loginResponse2 = CourierClient.login(credsWrong);
        check.impossibleRegistrationCourierWithWrongLogin(loginResponse2);
    }

    @After
    public void deleteCourier() {
        client.delete(courierId);
    }

}
