package SwagLabsDemo.AbstractComponants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstraactComponants {

    WebDriver driver;

    public AbstraactComponants(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="cancel")
    WebElement CancelButton;

    public String Cancel()
    {
          CancelButton.click();
          return driver.getCurrentUrl();
    }


}
