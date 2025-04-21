package ui;

import com.vladislav.testAutomationReportPortal.pages.DashboardPage;
import com.vladislav.testAutomationReportPortal.pages.LoginPage;
import com.vladislav.testAutomationReportPortal.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

class ChromeTest {
    private static final Logger logger = LoggerFactory.getLogger(ChromeTest.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        logger.info("Initializing Chrome test");
        driver = DriverFactory.createChromeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    void testFullWorkflow() {
        logger.info("Starting full workflow test");
        loginPage.loginAs("default", "1q2w3e");
        dashboardPage.openDashboard();
        dashboardPage.selectDashboard();
        dashboardPage.addNewWidget("testWidget");
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
        logger.info("Workflow test completed");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Chrome test cleanup done");
        }
    }
}