    package SwagLabsDemo.Tests;

    import SwagLabsDemo.PageObjects.*;
    import SwagLabsDemo.TestComponants.BaseTest;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;

    import java.io.IOException;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class EndToEndTest extends BaseTest {

        @Test(groups ="Purchase", dataProvider = "GetData")
        public void SubmitOrderr(HashMap<String,String> input) {

            ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
            ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }

        @DataProvider
        public Object[][] GetData() throws IOException {
            List<HashMap<String,String>> data = GetJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
            return new Object[][]{{data.get(0)},{data.get(1)},{data.get(2)}};
        }
    }





