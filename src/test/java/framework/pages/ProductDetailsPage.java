package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    private By productDetailsHeader = By.xpath("//div[@data-test='inventory-item-name']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductDetailsHeaderText() {
        return find(productDetailsHeader)
                .getText();
    }
}
