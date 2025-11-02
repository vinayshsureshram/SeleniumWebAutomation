package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Accounts")
public class AccountsTests extends BaseTest {

    @Test
    @TmsLink("TEST-67890")
    void testAccountDetails() {
        loginPage.enterUsername("testbanker")
                .enterPassword("tester")
                .clickLoginButton();
        accountsPage.clickAccountNumber("27441");
        Assert.assertEquals(accountDetailsPage.getAccountDetailsHeaderText(), "Account Details");
    }
}
