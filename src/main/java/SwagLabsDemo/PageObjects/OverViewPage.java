package SwagLabsDemo.PageObjects;

import SwagLabsDemo.AbstractComponants.AbstraactComponants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OverViewPage extends AbstraactComponants {

    WebDriver driver;

    public OverViewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = ".cart_item")
    List<WebElement> CheckOutItems;

    @FindBy(id ="finish" )
    WebElement FinishButton;

    @FindBy(id="cancel")
    WebElement CancelButton;

    By ItemName = By.cssSelector(".inventory_item_name");


    public boolean CheckProductIsDisplay(String ProductName)
    {
            for(WebElement CheckOutItem : CheckOutItems)
            {
                    String CheckOutItemName = CheckOutItem.findElement(ItemName).getText();
                     if (CheckOutItemName.equalsIgnoreCase(ProductName))
                    {
                      return true;
                    }
            }
            return false;
    }

    public ConfirmationPage GoToConfirmationPage()
    {
        FinishButton.click();
        return new ConfirmationPage(driver);
    }
}