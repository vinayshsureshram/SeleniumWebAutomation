package tests;

import framework.pages.Common;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login")
public class LoginTests extends BaseTest {
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    @TmsLink("TEST-12345")
    void testLoginFlow() {
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Common.loginToApp(USERNAME, PASSWORD);
        Assert.assertEquals(productsPage.getProductsHeader(), "Products");
    }
}
