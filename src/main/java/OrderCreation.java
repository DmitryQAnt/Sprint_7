import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;


public class OrderCreation extends BaseClient {
    public static final String ORDER_PATH = "/api/v1/orders";
    //  public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    @Step("Creation of order")
    public static ValidatableResponse create(Order order) {
        return getRequestSpecification()
                .log().all()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Receiving of order list")
    public static ValidatableResponse receiveOrderList(Order order) {
        return getRequestSpecification()
                .log().all()
                .body(order)
                .when()
                .get(ORDER_PATH)
                .then();
    }

    public static class OrderGenerator {
        @Step("Generator of orders for parametrized test")
        public static Order createDefaultOrder() {
            return new Order("Mitia", "Mur", "Zelenograd", "Krukovo", "+74444", "5", "2020-02-02", "I am home", new String[]{});
        }

        @Step("Changing of color combination")
        public static Order setColor(String color) {
            Order order = createDefaultOrder();
            order.setColor(new String[]{color});
            return order;
        }
    }
}
