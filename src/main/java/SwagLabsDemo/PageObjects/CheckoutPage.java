package SwagLabsDemo.PageObjects;

import SwagLabsDemo.AbstractComponants.AbstraactComponants;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstraactComponants {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="first-name")
    WebElement FirstName;

    @FindBy(id="last-name")
    WebElement LastName;

    @FindBy(id="postal-code")
    WebElement PostalCode;

    @FindBy(id="continue")
    WebElement Continue;

    @FindBy(css = "h3[data-test='error']")
    WebElement ErrorMessage;


    public void EnterInformation(String firstname, String lastname, String Postalcode)
    {
            FirstName.sendKeys(firstname);
            LastName.sendKeys(lastname);
            PostalCode.sendKeys(Postalcode);
    }

    public OverViewPage GoToCheckoutOverViewPage()
    {
            Continue.click();
            return new OverViewPage(driver);
    }

    public String GetInformationErrorMessage()
    {
           return ErrorMessage.getText();

    }

}
