package org.example.pages;

import org.example.config.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage {

/**

 Page factory


//    @FindBy(xpath = "//input[@id='searchboxTrigger']")
//    private WebElement searchInput1;
//
//    @AndroidFindBy(xpath = "//input[@id='searchboxTrigger']")
//    @iOSFindBy(predicate ="")
//    private WebElement searchInput2;

*/


    /**
     * Page object model
     */
    private final By searchInput = By.xpath("//input[@id='searchboxTrigger']");
    private final By selectUnder32Button = By.xpath("//body/div[3]/div[2]/div[1]/section[1]/div[1]/div[3]/div[2]/div[5]/div[1]/div[1]/div[1]/div[3]/a[1]/div[1]/img[1]");

    public HomePage searchProduct() {
        DriverManager.getDriver().findElement(searchInput).sendKeys("samsung");
        DriverManager.getDriver().findElement(searchInput).sendKeys(Keys.ENTER);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public HomePage selectFirstResult() {
        DriverManager.getDriver().findElement(selectUnder32Button).click();
        return this;
    }

    public void verifyProductName() {
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("samsung"));
    }
}
