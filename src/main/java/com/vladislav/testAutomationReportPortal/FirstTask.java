package com.vladislav.testAutomationReportPortal;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class FirstTask {
        private static final Logger logger = LoggerFactory.getLogger(FirstTask.class);

        public static void main(String[] args) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--incognito");

            WebDriver driver = new ChromeDriver(chromeOptions);

            try {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                logger.info("Navigating to login page.");
                driver.get("https://demo.reportportal.io/ui/#login");
                WebElement loginInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
                loginInput.clear();
                loginInput.sendKeys("default");

                WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
                passwordInput.clear();
                passwordInput.sendKeys("1q2w3e");
                driver.findElement(By.cssSelector("button[type='submit']")).click();

                logger.info("Logged in successfully, navigating to dashboard.");
                driver.get("https://demo.reportportal.io/ui/#default_personal/dashboard/");

                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(@href, '#default_personal/dashboard/') and contains(@class, 'dashboardTable__name')]")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

                logger.info("Clicking on 'Add new widget' button.");
                WebElement addNewWidget = driver.findElement(By.xpath("//button[.//span[text()='Add new widget']]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewWidget);

                logger.info("Clicking on radio button for widget type.");
                WebElement radioButton = driver.findElement(By.xpath("(//*[@id=\"modal-root\"]/div/div[1]/div/div[1]/div/div[2]/div[2]/div[1]/form/div/div[15]/label/span[1])"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);

                logger.info("Clicking on 'Next step' button.");
                WebElement nextStepButton = driver.findElement(By.xpath("(//span[contains(text(), 'Next step')])"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextStepButton);

                WebElement widgetName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter launch name']")));
                widgetName.sendKeys("testWidget");

                logger.info("Entering widget name and proceeding to next step.");
                WebElement nextStepButton2 = driver.findElement(By.xpath("(//span[contains(text(), 'Next step')])"));
                nextStepButton2.click();
                WebElement addButton = driver.findElement(By.xpath("//button[normalize-space(text())='Add']"));
                addButton.click();

                logger.info("Checking if widget 'testWidget' is created.");

                WebElement createdWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'testWidget')]")));
                if (createdWidget != null) {
                    logger.info("Widget has been successfully created.");
                } else {
                    logger.warn("Widget was not created.");
                }

            } catch (Exception e) {
                logger.error("An error occurred during the execution.", e);
            } finally {
                driver.quit();
            }
        }
    }
