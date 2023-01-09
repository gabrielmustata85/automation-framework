package org.example.listeners;

import com.aventstack.extentreports.Status;
import org.example.config.DriverManager;
import org.example.reporting.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

public class TestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("**************** Start *****************");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("**************** PASS *****************");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("**************** FAIL *****************");

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(DriverManager.getDriver())).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(Status.INFO, "Test Failed ",
                ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(DriverManager.getDriver())).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(Status.INFO, "Test Skipped",
                ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("**************** STARTING TEST SUITE ****************");
    }

    @Override
    public void onFinish(ITestContext context) {
        String totalTestsRun = String.valueOf(context.getPassedTests().size() + context.getFailedTests().size()
                + context.getSkippedTests().size());
        DriverManager.killDriver();
    }
}
