import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    @Step("Generating courier without password field")
    public static Courier CourierWithoutPasswordField() {
        return new Courier("mitia123", null, "simple");
    }

    @Step("Generating of random courier login")
    public static Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(5, 10), "12345", "simple");
    }

    @Step("Generating courier with constant creds")
    public static Courier CourierBasic() {
        return new Courier("mitia1234", "1234", null);
    }

    @Step("Generating courier with different password")
    public static Courier CourierWrongPassword() {
        return new Courier("mitia1234", "12345", null);
    }

    @Step("Generating courier with empty login field")
    public static Courier courierWithoutLogin() {
        return new Courier("", "1234", null);
    }
}
