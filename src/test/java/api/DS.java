//package api;
//
//import com.vladislav.testAutomationReportPortal.utils.ApiData;
//import helpers.RestAssuredHelper;
//import io.qameta.allure.*;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import java.util.List;
//import static helpers.RestAssuredHelper.RequestType.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Epic("Dashboard Management")
//@Feature("Dashboard API Operations")
//public class DS {
//
//    @BeforeAll
//    @Step("Initialize API settings")
//    static void setup() {
//        new RestAssuredHelper();
//    }
//
//    @Test
//    @DisplayName("Positive: Create new dashboard")
//    @Step("Initial5555ize API settings")
//    @Story("User creates valid dashboard")
//    @Severity(SeverityLevel.CRITICAL)
//    void createDashboardPositiveTest() {
//        Response response = executeCreateRequest();
//        Integer createdId = extractDashboardId(response);
//        verifyDashboardInList(createdId);
//    }
//
//    @Test
//    @DisplayName("Negative: Create dashboard without required fields")
//    @Story("Validation of mandatory fields")
//    @Severity(SeverityLevel.NORMAL)
//    void createDashboardNegativeTest() {
//        Response response = new RestAssuredHelper()
//                .withBasePath("/default_personal/dashboard")
//                .withBody(new Dashboard())
//                .executeAndValidate(POST, 400);
//
//        validateErrorMessage(response);
//    }
//
//    @Step("Execute dashboard creation request")
//    private Response executeCreateRequest() {
//        return new RestAssuredHelper()
//                .withBasePath("/default_personal/dashboard")
//                .withBody(RestAssuredHelper.generateTestDashboard())
//                .executeAndValidate(POST, 201);
//    }
//
//    @Step("Extract dashboard ID from response")
//    private Integer extractDashboardId(Response response) {
//        return response.path("id");
//    }
//
//    @Step("Verify dashboard exists in list")
//    private void verifyDashboardInList(Integer dashboardId) {
//        List<Integer> ids = new RestAssuredHelper()
//                .withBasePath("/default_personal/dashboard")
//                .withQueryParam("page.size", 100)
//                .withQueryParam("sort", "id,desc")
//                .executeAndValidate(GET, 200)
//                .jsonPath()
//                .getList("content.id", Integer.class);
//
//        assertTrue(ids.contains(dashboardId),
//                "Dashboard ID " + dashboardId + " not found");
//    }
//
//    @Step("Validate error message in response")
//    private void validateErrorMessage(Response response) {
//        String error = response.path("message");
//        assertNotNull(error, "Error message missing");
//        assertTrue(error.contains("Field 'name'"),
//                "Incorrect validation message");
//    }
//}