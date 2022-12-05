package tests.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.time.Duration;

import static org.example.config.DriverManager.getDriver;

public class LoginTest extends BaseTest {

    @Test
    public void test() throws InterruptedException {
//        getDriver().findElement(By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")).click();
        Thread.sleep(1200);
    }

    @Test
    public void explicitWait() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/section[@id='form']/div[1]/div[1]/div[3]/div[1]/form[1]/input[2]")));
        getDriver().findElement(By.xpath("//input[@id='searchboxTrigger']")).sendKeys("samsung");
        Thread.sleep(500);
        getDriver().findElement(By.xpath("//input[@id='searchboxTrigger']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }
}
