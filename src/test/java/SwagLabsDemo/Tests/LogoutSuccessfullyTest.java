package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.*;
import SwagLabsDemo.TestComponants.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LogoutSuccessfullyTest extends BaseTest {

    @Test(dataProvider = "GetData")
    public void logout(HashMap<String,String> input){

        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        CartPage cartPage =productCatalogue.GoToCart();
        CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
        checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
        OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
        ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
        Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
    }

    @DataProvider
    public Object[][] GetData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
        return new Object[][]{{data.get(0)}};
    }
}
