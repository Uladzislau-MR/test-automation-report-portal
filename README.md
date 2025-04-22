# test-automation-report-portal
A demonstration project for test automation using the demo.reportportal.io platform.

This project contains UI and API tests for ReportPortal, using:
## 🧪 Features

- UI testing with Selenium WebDriver
- API testing using RestAssured
- Logging with SLF4J + Logback
- JUnit 5 test framework
- ReportPortal integration

---

## 🔐 API Testing Setup

To run API tests, you need to generate a personal API token and insert it into the project configuration.

### 1. Generate an API Token

Go to: https://demo.reportportal.io/ui/#userProfile/apiKeys  
Click **"Generate API Key"**  
Copy the generated token

### 2. Add the Token to the Project

Open the file:  
`src/main/java/com/vladislav/testAutomationReportPortal/utils/ApiData.java`

Locate the `Auth` class and replace the token value in the `TOKEN` field with the generated token:

```java
public class ApiData {
    


    public static class Auth {
      
        public static final String TOKEN = "PASTE_YOUR_API_TOKEN_HERE"; // Replace with your generated API token
    }



