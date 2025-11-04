package framework.pages;

import tests.BaseTest;

public class Common extends BaseTest {
    public static void loginToApp(String username, String password) {
        loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();
    }
}
