package org.example.helpers;


import org.example.config.DriverManager;
import org.openqa.selenium.WebDriver;

public class BrowserHelper {

    WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void refreshBrowser(){
        getDriver().navigate().refresh();
    }

    public void goForward(){
        getDriver().navigate().forward();
    }

    public void goBack(){
        getDriver().navigate().back();
    }
}
