package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private By productsHeaderText = By.cssSelector(".title");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductsHeader() {
        return find(productsHeaderText)
                .getText();
    }

    public void clickProduct(String productName) {
    find(By.xpath("//div[@class='inventory_item_name ' and text()='" + productName + "']"));
    }
}
