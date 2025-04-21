package com.vladislav.testAutomationReportPortal.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {

    public static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        return new ChromeDriver(options);
    }

    public static WebDriver createFirefoxDriver() {

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("moz:webdriverClick", false);


        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.webdriver.enabled", false);
        profile.setPreference("useAutomationExtension", false);
        profile.setPreference("general.platform.override", "Win32");
        options.setProfile(profile);
        options.addArguments("--disable-blink-features=AutomationControlled");
        return new FirefoxDriver(options);
    }

    public static WebDriver createEdgeDriver() {
        return new EdgeDriver();
    }
}

