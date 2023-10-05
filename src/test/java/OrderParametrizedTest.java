import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderParametrizedTest {

    private final Assertions check = new Assertions();
    private final Order order;
    private final String testName;
    public OrderParametrizedTest(String testName, Order order) {
        this.order = order;this.testName = testName;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {"Only BLACK color", OrderCreation.OrderGenerator.setColor("BLACK") },
                {"Both BLACK and GREY", OrderCreation.OrderGenerator.setColor("GREY, BLACK") },
                {"Field without data", OrderCreation.OrderGenerator.setColor("") },
        });
    }
    @Test
    @DisplayName("Testing of different scooter's colors and without")
    @Description("Parametrized test for /api/v1/orders endpoint")
    public void checkColors() {
        ValidatableResponse response = OrderCreation.create(order);
        check.orderRecieved(response);

}}
