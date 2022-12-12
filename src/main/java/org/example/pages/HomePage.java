package org.example.pages;

import org.example.config.DriverManager;
import org.example.helpers.BasePageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePageHelper {

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
        action.typeText(searchInput, "samsung");
//        action.typeText(searchInput, String.valueOf(Keys.ENTER));
        DriverManager.getDriver().findElement(searchInput).sendKeys(Keys.ENTER);
        waitHelper.waitFor(2500);
        return this;
    }

    public HomePage selectFirstResult() {
        action.clickOn(selectUnder32Button);
        return this;
    }

    public void verifyProductName() {
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("testng"), "No product with this name");
    }
}
