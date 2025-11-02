package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage extends BasePage {

    private By accountDetailsMessage = By.className("error");

    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public void clickAccountNumber(String accNum) {
        clickElement(By.xpath("//a[contains(@href, '" + accNum + "')]"));
    }
}
