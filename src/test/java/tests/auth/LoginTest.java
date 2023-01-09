package tests.auth;

import org.testng.annotations.Test;
import tests.BaseTest;
import tests.data.Data;

public class LoginTest extends BaseTest {

    @Test(description = "demo test", groups = {"grup", "regression"}, priority = 1)
    public void searchProduct() {
        homePage.searchProduct()
                .selectFirstResult()
                .verifyProductName();
    }

}
