package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.CartPage;
import SwagLabsDemo.PageObjects.CheckoutPage;
import SwagLabsDemo.PageObjects.OverViewPage;
import SwagLabsDemo.PageObjects.ProductCatalogue;
import SwagLabsDemo.TestComponants.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CheckoutInformationErrorValidations extends BaseTest {

    @Test(dataProvider = "GetData")
    public void InformationErrorValidations(HashMap<String,String> input)
    {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        CartPage cartPage =productCatalogue.GoToCart();
        Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
        CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
        checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
        checkoutPage.GoToCheckoutOverViewPage();
        Assert.assertEquals(checkoutPage.GetInformationErrorMessage(),input.get("error-message"));
    }

    @DataProvider
    public Object[][] GetData() throws IOException {
        List<HashMap<String,String>> data = GetJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\InformationErrorValidationData.json");
        return new Object[][]{{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)}};
    }
}
