package SwagLabsDemo.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (css = "input[placeholder='Username")
    WebElement UserName;

    @FindBy(css ="input[placeholder='Password']" )
    WebElement Password;

    @FindBy(id = "login-button")
    WebElement LoginButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement loginError;

    public void goTo()
    {
        driver.get("https://www.saucedemo.com/");
    }

    public ProductCatalogue LoginApplication(String username , String password) {

        UserName.sendKeys(username);
        Password.sendKeys(password);
        LoginButton.click();
        return new ProductCatalogue(driver);
    }

    public String GetLoginErrorMessage()
    {
       String LoginErrorMessage = loginError.getText();
       return LoginErrorMessage;
    }


}
