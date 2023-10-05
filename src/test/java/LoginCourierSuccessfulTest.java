import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginCourierSuccessfulTest {
    private final CourierClient client = new CourierClient();
    private final Assertions check = new Assertions();
    private Courier courier;
    private  int courierId;


    @Test
    @DisplayName("Check to login new courier  with valid credentials")
    @Description("Successful login test for /api/v1/courier/login endpoint")
    public void checkCreateNewCourierWithUniqueData() {
        courier = CourierGenerator.random();
        client.create(courier);
        ValidatableResponse loginResponse = client.login(Credentials.from(courier));
        courierId = client.getLoggedInCourierId(loginResponse);
        check.loggedInSuccesfuly(loginResponse);
    }
    @After
    public void deleteCourier(){
        client.delete(courierId);
    }

}
