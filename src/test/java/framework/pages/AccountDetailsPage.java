package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDetailsPage extends BasePage {

    private By accountDetailsHeader = By.xpath("//div[@id='accountDetails']/h1");

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountDetailsHeaderText() {
        return find(accountDetailsHeader)
                .getText();
    }
}
