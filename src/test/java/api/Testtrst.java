package api;

public class Testtrst {
    //package api;
//
//import com.vladislav.testAutomationReportPortal.utils.ApiData;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.Random;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class DashboardApiTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(DashboardApiTest.class);
//
//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = ApiData.Endpoints.URL;
//    }
//
//    @Test
//    public void createDashboardPositiveTest() {
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        logger.info("\n=== Test 2: Creating a new Dashboard ===");
//
//        String projectName = "default_personal";
//        Random random = new Random();
//        int randomSuffix = random.nextInt(20, 1000);
//        String dashboardName = "testBoard" + randomSuffix;
//
//        logger.debug("Generating test dashboard data:\nName: {}\nDescription: tv", dashboardName);
//
//        Dashboard dashboard = new Dashboard(dashboardName, "test");
//
//        logger.info("Sending POST request to create dashboard");
//        Response response = given()
//                .basePath("/" + projectName + "/dashboard")
//                .header("Authorization", "Bearer " + ApiData.Auth.TOKEN)
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .body(dashboard)
//                .when()
//                .post()
//                .then()
//                .statusCode(201)
//                .log().ifError()
//                .extract()
//                .response();
//
//
//
//
//
//
//
//
//
//
//
//
//
//        Integer createdDashboardId = response.path("id");
//        logger.info("Successfully created dashboard. ID: {}", createdDashboardId);
//
//        logger.debug("Retrieving dashboard list with parameters:\nPage size: 100\nSort order: ID descending");
//        Response listResponse = given()
//                .basePath("/" + projectName + "/dashboard")
//                .header("Authorization", "Bearer " + ApiData.Auth.TOKEN)
//                .queryParam("page.size", 100)
//                .queryParam("sort", "id,desc")
//                .accept(ContentType.JSON)
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .log().ifValidationFails()
//                .extract()
//                .response();
//
//        List<Integer> dashboardIds = listResponse.jsonPath().getList("content.id", Integer.class);
//
//        logger.debug("Verification details:\nExpected ID: {}\nTotal dashboards: {}",
//                createdDashboardId, dashboardIds.size());
//
//        assertTrue(dashboardIds.contains(createdDashboardId),
//                "Dashboard ID " + createdDashboardId + " not found in list");
//
//        logger.info("Test passed - Dashboard created and verified");
//    }
//
//
//    @Test
//    public void createDashboardNegativeTest() {
//        Logger logger = LoggerFactory.getLogger(DashboardApiTest.class);
//
//        logger.info("\n=== Test 3: Create Dashboard with Insufficient Parameters ===");
//        String projectName = "default_personal";
//
//        logger.info("Creating empty dashboard payload");
//        Dashboard dashboard = new Dashboard();
//        logger.debug("Payload: {}", dashboard);
//
//        logger.info("Sending POST with missing required fields");
//        Response response = given()
//                .basePath("/" + projectName + "/dashboard")
//                .header("Authorization", "Bearer " + ApiData.Auth.TOKEN)
//                .contentType(ContentType.JSON)
//                .body(dashboard)
//                .when()
//                .post()
//                .then()
//                .statusCode(400)
//                .log().ifError()
//                .extract()
//                .response();
//
//        logger.info("Checking error response");
//        String errorMessage = response.jsonPath().getString("message");
//        logger.debug("Error message: {}", errorMessage);
//        assertNotNull(errorMessage, "Error message should be present");
//
//        logger.info("Ensuring no invalid dashboard created");
//        List<Integer> dashboardIds = given()
//                .basePath("/" + projectName + "/dashboard")
//                .header("Authorization", "Bearer " + ApiData.Auth.TOKEN)
//                .queryParam("page.size", 100)
//                .get()
//                .jsonPath()
//                .getList("content.id", Integer.class);
//
//        logger.debug("Current dashboards count: {}", dashboardIds.size());
//
//        Integer invalidDashboardId = response.jsonPath().getObject("id", Integer.class);
//        if(invalidDashboardId != null) {
//            logger.warn("Unexpected ID in error response: {}", invalidDashboardId);
//            assertFalse(dashboardIds.contains(invalidDashboardId),
//                    "Invalid dashboard ID found in list");
//        }
//
//        logger.info("Negative test passed - validation works correctly");
//    }
//
//
//}
//
//
//
//
//
//

}
