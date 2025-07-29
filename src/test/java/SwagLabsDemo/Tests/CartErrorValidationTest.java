package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.CartPage;
import SwagLabsDemo.PageObjects.ProductCatalogue;
import SwagLabsDemo.TestComponants.BaseTest;
import SwagLabsDemo.TestComponants.RetryFailedTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CartErrorValidationTest extends BaseTest {

    @Test(dataProvider = "GetData",retryAnalyzer = RetryFailedTest.class)
    public void EmptyCart(HashMap<String,String> input)
    {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        CartPage cartPage =productCatalogue.GoToCart();
        cartPage.GoToCheckoutPage();
        Assert.assertEquals(cartPage.GetEmptyCartErrorMessage(),"Error : Empty Cart");
    }

    @DataProvider
    public Object[][] GetData() throws IOException {
        List<HashMap<String,String>> data = GetJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
        return new Object[][]{{data.get(0)}};
    }
}
