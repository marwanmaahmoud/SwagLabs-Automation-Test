package SwagLabsDemo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class P03_CartPage {

    WebDriver driver;


    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cart_item")
    List<WebElement> CartItems;

    @FindBy(id = "checkout")
    WebElement CheckoutButton;

    @FindBy(css = ".cart_list")
    WebElement EmptyCartError;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    By itemNameBy = By.cssSelector(".inventory_item_name");
    By removeButton = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");
    public boolean VerifyProductInTheCart(String ProductName)
    {
        boolean found = false;
        for (WebElement item : CartItems){
            String ItemName = item.findElement(itemNameBy).getText();
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
            String ItemName = item.findElement(itemNameBy).getText();
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
        Item.findElement(removeButton).click();


    }

    public P04_CheckoutPage GoToCheckoutPage()
    {
        CheckoutButton.click();
        return new P04_CheckoutPage(driver);

    }

    public void backToHomePage()
    {
        continueShoppingButton.click();
    }

    public String GetEmptyCartErrorMessage()
    {
        return EmptyCartError.getText();
    }


}
