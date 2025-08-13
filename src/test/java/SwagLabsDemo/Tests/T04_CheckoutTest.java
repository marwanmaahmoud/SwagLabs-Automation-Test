package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.P03_CartPage;
import SwagLabsDemo.PageObjects.P04_CheckoutPage;
import SwagLabsDemo.PageObjects.P02_ProductCatalogue;
import SwagLabsDemo.TestComponants.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class T04_CheckoutTest extends BaseTest {

    @Test(dataProvider = "GetData")
    public void informationErrorValidations(HashMap<String,String> input)
    {
        P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        P03_CartPage cartPage =productCatalogue.GoToCart();
        Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
        P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
        checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
        checkoutPage.GoToCheckoutOverViewPage();
        Assert.assertEquals(checkoutPage.GetInformationErrorMessage(),input.get("error-message"));
    }

    @DataProvider
    public Object[][] GetData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\InformationErrorValidationData.json");
        return new Object[][]{{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)}};
    }
}
