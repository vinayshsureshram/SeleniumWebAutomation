package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login")
public class LoginTests extends BaseTest {

    @Test
    @TmsLink("TEST-12345")
    void testLoginFlow() {
        Assert.assertEquals(loginPage.getPageTitle(),"ParaBank | Welcome | Online Banking");
        loginPage.enterUsername("testbanker")
                .enterPassword("tester")
                .clickLoginButton();
        Assert.assertEquals(accountsPage.getPageTitle(), "ParaBank | Accounts1 Overview");
    }
}
