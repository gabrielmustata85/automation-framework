package org.example.helpers;

import org.example.config.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitHelper {

    static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    //implicit wait
    public void implicitWait(Duration timeoutDuration){
        getDriver().manage().timeouts().implicitlyWait(timeoutDuration);
    }


    //fluent wait
    private static Wait<WebDriver> fluentWait(Duration timeOutDuration){
        return new FluentWait<>(getDriver())
                .withTimeout(timeOutDuration)
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);
    }

    public void waitForElement(ExpectedCondition<?> condition, Duration timeoutDuration) {
        try {
            fluentWait(timeoutDuration).until(condition);
        } catch (TimeoutException e) {
            throw e;
        }
    }

    public void waitForElementVisibility(By by, Duration timeOutDuration){
        waitForElement(ExpectedConditions.visibilityOfElementLocated(by), timeOutDuration);
    }

    public void isElementClickable(By by , Duration timeout){
        waitForElement(ExpectedConditions.elementToBeClickable(by), timeout);
    }

    public void isElementEnabled(By by, Duration timeout){
        waitForElement(ExpectedConditions.elementToBeSelected(by), timeout);
    }

    public void waitFor(long timeOut){
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

