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

public class ProductOverViewTest extends BaseTest {
    @Test(dataProvider = "GetData")
    public void VerifyProductIsDisplayed(HashMap<String,String> input) {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"), input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        CartPage cartPage = productCatalogue.GoToCart();
        CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
        checkoutPage.EnterInformation(input.get("firstname"), input.get("lastname"), input.get("PostalCode"));
        OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();
        ;
        Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));

    }

    @DataProvider
    public Object[][] GetData() throws IOException {
        List<HashMap<String,String>> data = GetJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
        return new Object[][]{{data.get(0)}};
    }

}
