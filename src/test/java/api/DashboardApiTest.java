package api;


import com.vladislav.testAutomationReportPortal.utils.ApiData;
import helpers.RestAssuredHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static helpers.RestAssuredHelper.RequestType.POST;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Dashboard Management")
@Feature("Dashboard API Operations")
public class DashboardApiTest {
    Logger logger = LoggerFactory.getLogger(DashboardApiTest.class);
    @Test
    @DisplayName("Positive: Create new dashboard")
    @Story("User creates valid dashboard")
    @Severity(SeverityLevel.CRITICAL)
    void createDashboardPositiveTest() {

        Dashboard testDashboard = RestAssuredHelper.generateTestDashboard();

        Response response = new RestAssuredHelper()
                .withBasePath(ApiData.Project.DASHBOARD_PROJECT_NAME + ApiData.Endpoints.DASHBOARD)
                .withBody(testDashboard)
                .executeAndValidate(POST, 201);
        Integer createdId = response.path("id");
        verifyDashboardWasCreated(createdId);
    }



private void verifyDashboardWasCreated(int dashboardId) {
    Response response = new RestAssuredHelper()
            .withBasePath(ApiData.Project.DASHBOARD_PROJECT_NAME + ApiData.Endpoints.DASHBOARD + "/" + dashboardId)
            .executeRequest(RestAssuredHelper.RequestType.GET);

    int statusCode = response.statusCode();

    if (statusCode == 200) {
        String dashboardName = response.jsonPath().getString("name");
        logger.info("Dashboard found: id={}, name='{}'", dashboardId, dashboardName);
    } else if (statusCode == 404) {
        logger.warn("Dashboard not found. ID: {}", dashboardId);
    } else {
        logger.error("Unexpected status code {} received when requesting dashboard ID {}", statusCode, dashboardId);
    }
}




    @Test
    @DisplayName("Negative: Create dashboard without required fields")
    @Story("Validation of mandatory fields")
    @Severity(SeverityLevel.NORMAL)
    void createDashboardNegativeTest() {

        Dashboard invalidDashboard = new Dashboard();


        Response response = new RestAssuredHelper()
                .withBasePath(ApiData.Project.DASHBOARD_PROJECT_NAME + ApiData.Endpoints.DASHBOARD)
                .withBody(invalidDashboard)
                .executeAndValidate(POST, 400);


        validateErrorMessage(response);
    }

    @Step("Validate error message in response")
    private void validateErrorMessage(Response response) {
        String errorMessage = response.jsonPath().getString("message");
        assertNotNull(errorMessage, "Error message is missing");
        assertTrue(errorMessage.contains("Field 'name'"),
                "Expected a validation error for the 'name' field, but got: " + errorMessage);
    }

}
