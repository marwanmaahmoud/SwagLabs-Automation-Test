package SwagLabsDemo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".inventory_item")
    List<WebElement> Products;

    @FindBy(css = ".shopping_cart_link" )
     WebElement CartButton;

    By AddToCartButton = By.cssSelector("button.btn_inventory");



    public List<WebElement> GetProductList() {
        return Products;
    }

    public WebElement GetProductName(String ProductName) {
        for (WebElement Product : Products) {

            String Name = Product.findElement(By.cssSelector(".inventory_item_name ")).getText();
            if (Name.equalsIgnoreCase(ProductName)) {
                return Product;
            }
        }
        return null;
    }

    public void AddProductToCart(String ProductName)
    {
        WebElement Product = GetProductName(ProductName);
        Product.findElement(AddToCartButton).click();
    }

    public CartPage GoToCart()
    {
       CartButton.click();
       return new CartPage(driver);
    }
}

