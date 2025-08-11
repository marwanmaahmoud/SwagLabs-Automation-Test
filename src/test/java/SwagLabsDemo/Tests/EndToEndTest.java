    package SwagLabsDemo.Tests;

    import SwagLabsDemo.PageObjects.*;
    import SwagLabsDemo.TestComponants.BaseTest;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;

    import java.io.IOException;
    import java.util.HashMap;
    import java.util.List;

    public class EndToEndTest extends BaseTest {

        @Test(groups ="Purchase", dataProvider = "GetData")
        public void submitOrder(HashMap<String,String> input) {

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

        @Test(dataProvider = "GetData")
        public void removeProduct(HashMap<String,String> input)
        {
            ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            productCatalogue.AddProductToCart(input.get("ProductName2"));
            productCatalogue.AddProductToCart(input.get("ProductName3"));
            CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName3")));
            cartPage.RemoveItem(input.get("ProductName2"));
            Assert.assertFalse(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName3")));
            CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName3")));
            ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }

        @Test(dataProvider = "GetData")
        public void cancelOrder(HashMap<String,String> input)
        {
            ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            String ReturnToCart = checkoutPage.Cancel();
            Assert.assertEquals(ReturnToCart,input.get("CartPageUrl"));
            cartPage.RemoveItem(input.get("ProductName"));
            cartPage.backToHomePage();
            productCatalogue.AddProductToCart(input.get("ProductName2"));
            productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName2")));
            ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }

        @DataProvider
        public Object[][] GetData() throws IOException {
            List<HashMap<String,String>> data = getJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
            return new Object[][]{{data.get(0)}};
        }
    }





