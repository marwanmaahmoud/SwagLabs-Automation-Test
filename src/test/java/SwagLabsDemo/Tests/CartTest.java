package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.CartPage;
import SwagLabsDemo.PageObjects.ProductCatalogue;
import SwagLabsDemo.TestComponants.BaseTest;
import SwagLabsDemo.Utilis.RetryFailedTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CartTest extends BaseTest {
    @Test(dataProvider = "GetData")
    public void verifyProductAddedToCart(HashMap<String,String> input)
    {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        CartPage cartPage =productCatalogue.GoToCart();
        Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
    }

    @Test(dataProvider = "GetData")
    public void removeProduct(HashMap<String,String> input)
    {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        CartPage cartPage =productCatalogue.GoToCart();
        Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
        cartPage.RemoveItem(input.get("ProductName"));
        Assert.assertFalse(cartPage.VerifyProductInTheCart(input.get("ProductName")));
    }

    @Test(dataProvider = "GetData",retryAnalyzer = RetryFailedTest.class)
    public void emptyCart(HashMap<String,String> input)
    {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        CartPage cartPage =productCatalogue.GoToCart();
        cartPage.GoToCheckoutPage();
        Assert.assertEquals(cartPage.GetEmptyCartErrorMessage(),"Error : Empty Cart");
    }

    @DataProvider
    public Object[][] GetData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\AddToCartData.json");
        return new Object[][]{{data.get(0)}};
    }

}
