package tests.auth;

import org.testng.annotations.Test;
import tests.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void searchProduct() throws InterruptedException {
        homePage.searchProduct()
                .selectFirstResult()
                .verifyProductName();
    }
}
