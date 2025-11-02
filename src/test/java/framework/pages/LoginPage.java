package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        typeValue(username, usernameField);
        return this;
    }

    public LoginPage enterPassword(String password) {
        typeValue(password, passwordField);
        return this;
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }
}
