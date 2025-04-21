package com.vladislav.testAutomationReportPortal.tests.uiTests;

import com.vladislav.testAutomationReportPortal.pages.DashboardPage;
import com.vladislav.testAutomationReportPortal.pages.LoginPage;
import com.vladislav.testAutomationReportPortal.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

public class FireFoxTest {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createFirefoxDriver();
       LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
       loginPage.loginAs("default", "1q2w3e");
       dashboardPage.openDashboard();
       dashboardPage.selectDashboard();
       dashboardPage.addNewWidget("testWidget");


    }



}
