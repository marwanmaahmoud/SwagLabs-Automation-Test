package SwagLabsDemo.Tests;

import SwagLabsDemo.PageObjects.CartPage;
import SwagLabsDemo.PageObjects.ProductCatalogue;
import SwagLabsDemo.TestComponants.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddToCartTest extends BaseTest {
    @Test(dataProvider = "GetData")
    public void VerifyProductAddedToCart(HashMap<String,String> input)
    {
        ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
        productCatalogue.AddProductToCart(input.get("ProductName"));
        CartPage cartPage =productCatalogue.GoToCart();
        Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
    }

    @DataProvider
    public Object[][] GetData() throws IOException {

        List<HashMap<String,String>> data = GetJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\AddToCartData.json");
        return new Object[][]{{data.get(0)}};
    }

}
