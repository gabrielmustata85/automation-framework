package tests;

import org.example.config.DriverManager;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {

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
        DriverManager.getDriver().navigate().to("https://www.emag.ro/");
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser(){
        DriverManager.killDriver();
    }
}
