package framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import com.google.common.base.Function;

public class BasePage {
    protected static WebDriver driver;
    public BasePage (WebDriver driver) {
        BasePage.driver = driver;
    }

    protected static WebElement find(final By locator, int... args) {

        int timeout = (args.length > 0 ? args[0] : 15);
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(Exception.class)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            WebElement webelement = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(locator);
                }
            });

            /* Alternatively
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
             */

            return webelement;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            throw new TimeoutException("Element not found: " + locator.toString());
        }
    }

    protected void clickElement(By locator) {
        find(locator).click();
    }

    protected void typeValue(String input, By locator) {
        find(locator).sendKeys(input);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
