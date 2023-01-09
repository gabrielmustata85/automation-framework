package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.example.config.ConfigProperties;
import org.example.config.DriverManager;
import org.example.pages.HomePage;
import org.example.reporting.ExtentManager;
import org.example.reporting.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Optional;

public class BaseTest {

    protected HomePage homePage;
    protected ConfigProperties properties;
    static String browser;
    static String hubUrl;

    private static ExtentReports extent;
    private static final ThreadLocal<Boolean> initializationStatus = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @BeforeSuite(alwaysRun = true)
    public void extentSetup(ITestContext context) {
        ExtentManager.createExtentReports();
    }

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

    @BeforeClass
    public void beforeClass(){

    }

    @BeforeMethod(alwaysRun = true)
    public void startExtent(Method method) {
        Test test = method.getAnnotation(Test.class);
        ExtentTestManager.createTest(method.getName(), test.description(), DriverManager.getDriverName());
        ExtentTestManager.getTest().assignAuthor("General_status");
    }

    @AfterMethod(alwaysRun = true)
    public void afterEachTestMethod(ITestResult result) {
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentTestManager.getTest().log(Status.PASS, "Test Finished");
                break;
            case ITestResult.FAILURE:
                ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
                break;
            case ITestResult.SKIP:
                Optional.ofNullable(result.getThrowable())
                        .ifPresentOrElse(
                                throwable -> ExtentTestManager.getTest().log(Status.SKIP,
                                        "Test Skipped due to " + result.getThrowable().getMessage()),
                                () -> ExtentTestManager.getTest().log(Status.SKIP,
                                        "Test Skipped due to failing/skipping in before hooks of tests")
                        );
                break;
            default:
                Optional.ofNullable(result.getThrowable())
                        .ifPresentOrElse(
                                throwable -> ExtentTestManager.getTest().log(Status.WARNING,
                                        "Test status unknown due to " + result.getThrowable().getMessage()),
                                () -> ExtentTestManager.getTest().log(Status.WARNING,
                                        "Test status Unknown due to preconditions or other dependencies")
                        );
        }
        ExtentManager.getInstance().flush();
    }
}
