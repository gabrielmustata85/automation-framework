package org.example.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

public class DriverManager {
    private static RemoteWebDriver driver;

    public static synchronized void setWebDriver(String browser, String hubUrl) {
        driver = setDriverFromConfig(browser, hubUrl);
        if (driver == null)
            Assert.fail("THE DRIVER IS NOT INITIALIZED, VERIFY MVN/VM -Driver PARAM ");
    }

    private static ChromeDriver setChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static FirefoxDriver setFirefoxDriver() {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        return new FirefoxDriver();
    }
    private static ChromeDriver setChromiumDriver() {
        WebDriverManager.chromiumdriver().setup();
        return new ChromeDriver();
    }

    private static EdgeDriver setEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private static SafariDriver setSafariDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }


    public static ChromeDriver setDriverManual() {
        final String driverBinaryPath = System.getProperty("Path");
        if (driverBinaryPath == null)
            Assert.fail("The binary for driver is null, double check the \"-DPath\" param");
        System.setProperty("webdriver.chrome.driver", driverBinaryPath);
        return (ChromeDriver) (driver = new ChromeDriver());
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void killDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    private static RemoteWebDriver setDriverFromConfig(String browser, String hubUrl) {
        final String DRIVER = System.getProperty("driver");
        if (DRIVER == null) {
            return driver = setChromeDriver();
        }


        switch (DRIVER) {
            case "Chrome":
                return setChromeDriver();
            case "Firefox":
                return setFirefoxDriver();
            case "Manual":
                return setDriverManual();
            case "Edge":
                return setEdgeDriver();
            case "Safari":
                return setSafariDriver();
            default:
                return driver;
        }
    }
}
