import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class TwoCouriersImpossibleToCreateTest {
    private final CourierClient client = new CourierClient();
    private final Assertions check = new Assertions();
    private Courier courier;

    @Test
    @DisplayName("Check impossible to create two similar couriers")
    @Description("Negative test for /api/v1/courier endpoint")
    public void checkCreateTwoSimilarCouriers() {
        courier = CourierGenerator.random();
        ValidatableResponse courierCreation = CourierClient.create(courier);
        ValidatableResponse courierSameData = CourierClient.create(courier);
        check.impossibleToRegistrateTwoSimilarCouriers(courierSameData);
    }

    @After
    public void deleteCourier() {
        Credentials creds = Credentials.from(courier);
        ValidatableResponse response = CourierClient.login(creds);
        client.delete(client.getLoggedInCourierId(response));
    }
}


