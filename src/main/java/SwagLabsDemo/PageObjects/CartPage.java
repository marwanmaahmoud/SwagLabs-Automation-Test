package SwagLabsDemo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    WebDriver driver;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cart_item")
    List<WebElement> CartItems;

    @FindBy(id = "checkout")
    WebElement CheckoutButton;

    @FindBy(css = ".item_pricebar button")
    WebElement RemoveButton;

    @FindBy(css = ".cart_list")
    WebElement EmptyCartError;

    By ItemNameBy = By.cssSelector(".inventory_item_name");
    public boolean VerifyProductInTheCart(String ProductName)
    {
        boolean found = false;
        for (WebElement item : CartItems){
            String ItemName = item.findElement(ItemNameBy).getText();
            if(ItemName.equalsIgnoreCase(ProductName))
            {
                found = true;
                return found;
            }
        }
        return found;
    }

    public WebElement GetItemName(String ProductName)
    {
        for(WebElement item : CartItems)
        {
            String ItemName = item.findElement(ItemNameBy).getText();
            if(ItemName.equalsIgnoreCase(ProductName))
            {
                return item;
            }
        }

        return null;
    }

    public void RemoveItem(String ProductName)
    {
        WebElement Item = GetItemName(ProductName);
        RemoveButton.click();

    }

    public CheckoutPage GoToCheckoutPage()
    {
        CheckoutButton.click();
        return new CheckoutPage(driver);

    }

    public String GetEmptyCartErrorMessage()
    {
        return EmptyCartError.getText();
    }


}
