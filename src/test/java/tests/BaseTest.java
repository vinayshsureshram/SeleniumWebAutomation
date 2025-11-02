package tests;

import framework.WebDriverManager;
import framework.pages.AccountDetailsPage;
import framework.pages.AccountsPage;
import framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    public static WebDriver driver = null;
    protected static LoginPage loginPage;
    protected static AccountsPage accountsPage;
    protected static AccountDetailsPage accountDetailsPage;

    public BaseTest() {
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    void setUp(String browser) {
        driver = WebDriverManager.getInstance(browser).getDriver();
        driver.navigate().to("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();

        //Initialise page models
        loginPage = new LoginPage(driver);
        accountsPage = new AccountsPage(driver);
        accountDetailsPage = new AccountDetailsPage(driver);
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
        WebDriverManager.quitDriver();
    }
}
