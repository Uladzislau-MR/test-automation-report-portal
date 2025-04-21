package com.vladislav.testAutomationReportPortal.pages;



import com.vladislav.testAutomationReportPortal.utils.ApiData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By loginInput = By.name("login");
    private By passwordInput = By.name("password");
    private By submitButton = By.cssSelector("button[type='submit']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void open() {
        driver.get(ApiData.Endpoints.BASE_URL + ApiData.Endpoints.LOGIN);
       wait.until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

    }


    public void enterLogin(String login) {
        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));
        loginField.clear();
        loginField.sendKeys(login);
    }


    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    public void submit() {
        driver.findElement(submitButton).click();
    }


    public void loginAs(String username, String password) {
        open();
        enterLogin(username);
        enterPassword(password);
        submit();
    }
}
