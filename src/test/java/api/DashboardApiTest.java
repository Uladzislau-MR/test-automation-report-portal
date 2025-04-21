package api;


import com.vladislav.testAutomationReportPortal.utils.ApiData;
import helpers.RestAssuredHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;


import static helpers.RestAssuredHelper.RequestType.GET;
import static helpers.RestAssuredHelper.RequestType.POST;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Dashboard Management")
@Feature("Dashboard API Operations")
public class DashboardApiTest {
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


    private void verifyDashboardWasCreated(Integer dashboardId) {
        List<Integer> ids = getDashboardIds();
        assertTrue(ids.contains(dashboardId), "Dashboard not found after creation");
    }



    private List<Integer> getDashboardIds() {
        return new RestAssuredHelper()
                .withBasePath(ApiData.Project.DASHBOARD_PROJECT_NAME + ApiData.Endpoints.DASHBOARD)
                .withQueryParam("page.size", 100)
                .executeAndValidate(GET, 200)
                .jsonPath()
                .getList("content.id", Integer.class);
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
