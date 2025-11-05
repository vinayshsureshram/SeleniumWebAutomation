package tests;

import framework.pages.Common;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Accounts")
public class ProductsTests extends BaseTest {
    private static final String USERNAME = testData.readTestData("LoginTests", "USERNAME");
    private static final String PASSWORD = testData.readTestData("LoginTests", "PASSWORD");

    @Test
    @TmsLink("TEST-67890")
    void testAccountDetails() {
        Common.loginToApp(USERNAME, PASSWORD);
        productsPage.clickProduct("Sauce Labs Backpack");
        Assert.assertEquals(productDetailsPage.getProductDetailsHeaderText(), "Sauce Labs Backpack");
    }
}
