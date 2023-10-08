import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateCourierSuccessfulTest {
    private final CourierClient client = new CourierClient();
    private final Assertions check = new Assertions();
    private Courier courier;

    @Test
    @DisplayName("Check to create new courier with valid data")
    @Description("Basic test for /api/v1/courier endpoint")
    public void checkCreateNewCourier() {
        courier = CourierGenerator.random();
        ValidatableResponse response = CourierClient.create(courier);
        check.assertCreatedSuccess(response);


    }

    @After
    public void deleteCourier() {
        Credentials creds = Credentials.from(courier);
        ValidatableResponse response = CourierClient.login(creds);
        client.delete(client.getLoggedInCourierId(response));
    }

}
