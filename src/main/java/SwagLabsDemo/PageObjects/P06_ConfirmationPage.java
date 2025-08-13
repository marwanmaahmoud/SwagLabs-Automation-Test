package SwagLabsDemo.PageObjects;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P06_ConfirmationPage {

    WebDriver driver;

    public P06_ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (css = ".complete-header")
    WebElement Confirmation;

    @FindBy (id = "react-burger-menu-btn")
    WebElement List;

    @FindBy (id = "logout_sidebar_link")
    WebElement LogoutButton;



    public String CheckConfirmationMessage()
    {
        String ConfirmationMessage = Confirmation.getText();
        return ConfirmationMessage;
    }

    public @Nullable String Logout()
    {
        List.click();
        LogoutButton.click();
        return driver.getCurrentUrl();
    }


}