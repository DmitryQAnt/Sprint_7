import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class OrderListNotNullTest {
    private final Order order = new Order();
    private final Assertions check = new Assertions();


    @Test
    @DisplayName("Check full order list request return ")
    @Description("Positive test for /api/v1/orders endpoint")
    public void checkIfOrderListIsNotNull() {
        var order = new Order();
        ValidatableResponse response = OrderCreation.receiveOrderList(order);
        check.responseContainListOfOrders(response);
    }
}
