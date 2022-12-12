package org.example.helpers;

import org.example.config.DriverManager;
import org.openqa.selenium.WebDriver;

public class TabsHelper {

    WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void switchTab(){
        getDriver().switchTo();
    }

}
