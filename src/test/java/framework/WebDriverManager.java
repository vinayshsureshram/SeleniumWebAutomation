package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverManager {

    private static volatile WebDriverManager instance;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager() { }

    public static WebDriverManager getInstance(String browser) {
        if(instance == null) {
            synchronized (WebDriverManager.class) {
                if(instance == null) {
                    instance = new WebDriverManager();
                }
            }
        }
        //Browser setup
        if(tlDriver.get() == null) {
            instance.initBrowser(browser);
        }
        return instance;
    }

    private void initBrowser(String browser) {
        switch(browser) {
            case "chrome":
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        tlDriver.get().quit();
        tlDriver.remove();
    }
}
