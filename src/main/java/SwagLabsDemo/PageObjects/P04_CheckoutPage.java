package SwagLabsDemo.PageObjects;

import SwagLabsDemo.AbstractComponants.AbstraactComponants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P04_CheckoutPage extends AbstraactComponants {

    WebDriver driver;

    public P04_CheckoutPage(WebDriver driver) {
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

    public P05_OverViewPage GoToCheckoutOverViewPage()
    {
            Continue.click();
            return new P05_OverViewPage(driver);
    }

    public String GetInformationErrorMessage()
    {
           return ErrorMessage.getText();

    }

}
