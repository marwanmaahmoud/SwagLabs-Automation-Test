package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.*;
import SwagLabsDemo.TestComponants.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class T06_ConfirmationTest extends BaseTest {
    @Test(dataProvider = "GetData",groups ="Purchase")
    public void checkConfirmationMessage(HashMap<String,String> input)
    {
        P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        P03_CartPage cartPage =productCatalogue.GoToCart();
        Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
        P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
        checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
        P05_OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
        Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
        P06_ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
        Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
    }
    @DataProvider
    public Object[][] GetData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
        return new Object[][]{{data.get(0)}};
    }
}
