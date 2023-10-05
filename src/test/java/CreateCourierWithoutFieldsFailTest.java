import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CreateCourierWithoutFieldsFailTest {
    private final CourierClient client = new CourierClient();
    private final Assertions check = new Assertions();
    private Courier courier;



    @Test
    @DisplayName("Check impossible to create new courier without Password Field")
    @Description("Negative test for /api/v1/courier endpoint")
    public void checkCreateCourierWithoutFields() {
        courier = CourierGenerator.CourierWithoutPasswordField();
        ValidatableResponse response = client.create(courier);
        check.assertCreatedFail(response);
    }

}
