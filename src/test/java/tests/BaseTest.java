package tests;

import framework.WebDriverManager;
import framework.pages.AccountDetailsPage;
import framework.pages.AccountsPage;
import framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTest {
    public static WebDriver driver = null;
    public static String platform = "Web";
    public static String formFactor = "MacOS";
    protected static LoginPage loginPage;
    protected static AccountsPage accountsPage;
    protected static AccountDetailsPage accountDetailsPage;

    public BaseTest() {
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    void setUp(String browser) throws Exception {
        driver = WebDriverManager.getInstance(browser).getDriver();
        driver.navigate().to("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();

        //Initialise page models
        loginPage = new LoginPage(driver);
        accountsPage = new AccountsPage(driver);
        accountDetailsPage = new AccountDetailsPage(driver);

        writeEnvironmentForAllureReport("QA", "ParaBank-25.0.0.3055", "Alpha");
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
        WebDriverManager.quitDriver();
    }

    /**
     * Generate the environment xml used by Allure to display details on the test run
     *
     * @param environment the tests were run against
     * @param appName     full file name of the application under test
     * @param appBuild    build type used (alpha/beta)
     * @throws IOException standard exception
     */
    private static void writeEnvironmentForAllureReport(String environment, String appName, String appBuild) throws IOException, IOException {
        String environmentXml = "<environment>\n";

        if (appName != null) {
            environmentXml = environmentXml
                    + "    <parameter>\n"
                    + "        <key>App under test: </key>\n"
                    + "        <value>" + appName + "</value>\n"
                    + "    </parameter>\n";
            Pattern pattern = Pattern.compile("(\\d+\\.)(\\d+\\.)(\\d+)");
            Matcher matcher = pattern.matcher(appName);
            if (matcher.find()) {
                environmentXml = environmentXml
                        + "    <parameter>\n"
                        + "        <key>Release Number: </key>\n"
                        + "        <value>" + matcher.group(0) + "</value>\n"
                        + "    </parameter>\n";
            }
        }
        if (appBuild != null) {
            environmentXml = environmentXml
                    + "    <parameter>\n"
                    + "        <key>Build: </key>\n"
                    + "        <value>" + appBuild + "</value>\n"
                    + "    </parameter>\n";
        }
        environmentXml = environmentXml
                + "    <parameter>\n"
                + "        <key>Platform: </key>\n"
                + "        <value>" + platform + "</value>\n"
                + "    </parameter>\n    <parameter>\n"
                + "        <key>Form factor: </key>\n"
                + "        <value>" + formFactor + "</value>\n"
                + "    </parameter>\n    <parameter>\n"
                + "        <key>Test region: </key>\n"
                + "        <value>" + environment + "</value>\n"
                + "    </parameter>\n"
                + "</environment>";

        File reportDir = new File("allure-results");
        if (!reportDir.mkdir())
            System.out.println("Allure results directory failed to be created");
        File file = new File(reportDir, "environment.xml");
        FileOutputStream outputStream = new FileOutputStream(file, false);
        byte[] strToBytes = environmentXml.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    public static String recordScreenDOM() {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            System.out.println("Failed to get page source: " + e.getMessage());
        }
        return null;
    }
}
