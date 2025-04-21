package com.vladislav.testAutomationReportPortal.pages;

import com.vladislav.testAutomationReportPortal.utils.ApiData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DashboardPage {
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.debug("Initialized DashboardPage with WebDriver instance");
    }

    public void openDashboard() {
        logger.info("Opening dashboard page");
        driver.get(ApiData.Endpoints.BASE_URL + ApiData.Project.PROJECT_NAME + ApiData.Endpoints.DASHBOARD);
        logger.debug("Navigated to: {}", driver.getCurrentUrl());
    }

    public void selectDashboard() {
        logger.info("Selecting dashboard from list");
        By dashboardLocator = By.xpath("//a[contains(@href, '#default_personal/dashboard/') and contains(@class, 'dashboardTable__name')]");

        try {
            WebElement dashboardLink = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardLocator));
            logger.debug("Dashboard link found, executing JavaScript click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dashboardLink);
            logger.info("Dashboard selected successfully");
        } catch (TimeoutException e) {
            logger.error("Failed to find dashboard link using locator: {}", dashboardLocator);
            throw e;
        }
    }

    public void addNewWidget(String widgetName) {
        logger.info("Starting widget creation process for widget: {}", widgetName);


        By addWidgetBtnLocator = By.xpath("//button[.//span[text()='Add new widget']]");
        clickElement(addWidgetBtnLocator, "Add new widget button");


        By widgetTypeLocator = By.xpath("(//div[contains(text(), 'Passing rate per launch')])");
        clickElement(widgetTypeLocator, "Widget type radio button");


        clickElement(By.xpath("//span[contains(text(), 'Next step')]"), "Next step button");


        By nameFieldLocator = By.xpath("//input[@placeholder='Enter launch name']");
        logger.debug("Entering widget name: {}", widgetName);
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameFieldLocator));
        nameField.sendKeys(widgetName);
        logger.info("Widget name '{}' entered successfully", widgetName);


        By nextStepLocator = By.xpath("//span[contains(text(), 'Next step')]");
        WebElement nextStepButton = driver.findElement(nextStepLocator);
        performBrowserSpecificClick(nextStepButton);


        clickElement(By.xpath("//button[normalize-space(text())='Add']"), "Add button");
        verifyWidgetCreation(widgetName);
    }

    private void clickElement(By locator, String elementName) {
        logger.debug("Clicking {} using locator: {}", elementName, locator);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Successfully clicked {}", elementName);
        } catch (TimeoutException e) {
            logger.error("Failed to click {} - element not clickable after 10 seconds", elementName);
            throw e;
        }
    }

    private void performBrowserSpecificClick(WebElement element) {
        String browser = driver.getClass().getSimpleName().toLowerCase();
        logger.debug("Performing browser-specific click for {}", browser);

        try {
            element.click();
            if (browser.contains("firefox")) {
                logger.info("Executing additional click for Firefox");
                element.click();
            }
        } catch (ElementClickInterceptedException e) {
            logger.warn("Click intercepted, attempting JavaScript click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

   public void verifyWidgetCreation(String widgetName) {
        logger.info("Verifying widget creation for: {}", widgetName);
        By widgetLocator = By.xpath(String.format("//*[contains(text(), '%s')]", widgetName));

        try {
            WebElement createdWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(widgetLocator));
            logger.info("Widget '{}' successfully created and visible", widgetName);
        } catch (TimeoutException e) {
            logger.error("Widget verification failed. Expected element not found: {}", widgetLocator);
            throw new AssertionError("Widget was not created successfully", e);
        }
    }
}