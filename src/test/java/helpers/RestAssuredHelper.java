package helpers;


import api.Dashboard;
import com.vladislav.testAutomationReportPortal.utils.ApiData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.*;

public class RestAssuredHelper {
    private RequestSpecification request;
    private String basePath;
    private final Map<String, Object> queryParams = new HashMap<>();
    private Object requestBody;

    public RestAssuredHelper() {
        this.request = given()
                .filter(new AllureRestAssured())
                .baseUri(ApiData.Endpoints.URL)
                .header("Authorization", "Bearer " + ApiData.Auth.TOKEN)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public RestAssuredHelper withBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public RestAssuredHelper withQueryParam(String paramName, Object value) {
        this.queryParams.put(paramName, value);
        return this;
    }

    public RestAssuredHelper withBody(Object body) {
        this.requestBody = body;
        return this;
    }

    public static Dashboard generateTestDashboard() {
        Random random = new Random();
        return new Dashboard(
                "testBoard" + random.nextInt(20, 1000),
                "test"
        );
    }

    public Response executeRequest(RequestType type) {
        RequestSpecification spec = request.basePath(basePath);

        if (!queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }

        if (requestBody != null) {
            spec.body(requestBody);
        }

        switch (type) {
            case GET:
                return spec.get();
            case POST:
                return spec.post();
            case DELETE:
                return spec.delete();
            default:
                throw new IllegalArgumentException("Unsupported request type: " + type);
        }
    }

    public Response executeAndValidate(RequestType type, int expectedStatusCode) {
        Response response = executeRequest(type);
        return response.then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public enum RequestType {
        GET, POST, DELETE
    }
}