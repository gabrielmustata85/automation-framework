package tests;

import org.example.config.ConfigProperties;
import org.example.config.DriverManager;
import org.example.pages.HomePage;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    protected HomePage homePage;
    protected ConfigProperties properties;
    static String browser;
    static String hubUrl;

    @BeforeTest(alwaysRun = true)
    public void getBrowserName(ITestContext context) {
        this.browser = context.getCurrentXmlTest().getParameter("browser");
        this.hubUrl = context.getCurrentXmlTest().getParameter("hubUrl");

        DriverManager.setWebDriver(browser, hubUrl);
    }

    @BeforeTest
    public void startBrowser(){
        homePage = new HomePage();
        properties = new ConfigProperties().readConfigProperties();
        DriverManager.getDriver().navigate().to(properties.getUrl());
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser(){
        DriverManager.killDriver();
    }
}
