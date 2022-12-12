package org.example.helpers;

import org.example.config.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ActionHelper {

    private final WaitHelper waitHelper = new WaitHelper();

    WebDriver getDriver() {
        return DriverManager.getDriver();
    }


    //click
    public void clickOn(By by) {
        waitHelper.isElementClickable(by, Duration.ofSeconds(20));
        getDriver().findElement(by).click();
    }

    //type
    public void typeText(By by, String text) {
        waitHelper.waitForElementVisibility(by, Duration.ofSeconds(30));
        getDriver().findElement(by).sendKeys(text);
    }

    //getText
    public void getText(By by) {
        getDriver().findElement(by).getText();
    }
}
