package testAutomationReportPortal.ui;

import com.vladislav.testAutomationReportPortal.pages.DashboardPage;
import com.vladislav.testAutomationReportPortal.pages.LoginPage;
import com.vladislav.testAutomationReportPortal.utils.ApiData;
import com.vladislav.testAutomationReportPortal.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EdgeTest {
    private static final Logger logger = LoggerFactory.getLogger(EdgeTest.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        logger.info("Initializing Edge driver");
        driver = DriverFactory.createEdgeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    void testEdgeWorkflow() {
        logger.info("Starting Edge browser workflow test");

        loginPage.loginAs(ApiData.Auth.USERNAME, ApiData.Auth.PASSWORD);
        logger.debug("Completed login");

        dashboardPage.openDashboard();
        logger.debug("Dashboard page opened");

        dashboardPage.selectDashboard();
        logger.debug("Dashboard selected");

        dashboardPage.addNewWidget("testWidget");
        logger.info("Widget creation attempted");

        assertTrue(driver.getCurrentUrl().contains("dashboard"),
                "Should remain on dashboard after operations");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            logger.info("Closing Edge driver");
            driver.quit();
        }
    }
}