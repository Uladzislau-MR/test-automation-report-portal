package api;


import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

@Epic("Smoke")
@Feature("Sanity")
public class SimpleTest {

    @Test
    @Story("Simple Assertion")
    @Description("Проверка, что true == true")
    @Severity(SeverityLevel.NORMAL)
    public void simpleTest() {
        assert true;
    }
}
