import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class LoginCourierWithoutFieldsFailTest {
    private final CourierClient client = new CourierClient();
    private final Assertions check = new Assertions();
    protected int courierId;
    private Courier courierWithoutLogin;

    @Test
    @DisplayName("Check impossible to login courier  without login field")
    @Description("Negative login test for /api/v1/courier/login endpoint")
    public void checkCourierLoginWithoutLogin() {
        courierWithoutLogin = CourierGenerator.courierWithoutLogin();
        Credentials basics = Credentials.from(courierWithoutLogin);
        ValidatableResponse response = CourierClient.login(basics);
        check.impossibleCourierLoginWithoutLoginField(response);
    }

}
