package com.vladislav.testAutomationReportPortal.tests.uITests;

import com.vladislav.testAutomationReportPortal.pages.DashboardPage;
import com.vladislav.testAutomationReportPortal.pages.LoginPage;
import com.vladislav.testAutomationReportPortal.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

public class ChromeTest {
    public static void main(String[] args) throws InterruptedException {
       WebDriver driver = DriverFactory.createChromeDriver();
       LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
       loginPage.loginAs("default", "1q2w3e");
       dashboardPage.openDashboard();
       dashboardPage.selectDashboard();
       dashboardPage.addNewWidget("testWidget");


    }



}
