package org.example.listeners;

import org.example.config.DriverManager;
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

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(DriverManager.getDriver())).getScreenshotAs(OutputType.BASE64);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("**************** SKIPPED *****************");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
