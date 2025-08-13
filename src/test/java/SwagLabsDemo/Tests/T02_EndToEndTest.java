    package SwagLabsDemo.Tests;

    import SwagLabsDemo.PageObjects.*;
    import SwagLabsDemo.TestComponants.BaseTest;
    import SwagLabsDemo.Utilis.RetryFailedTest;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;

    import java.io.IOException;
    import java.util.HashMap;
    import java.util.List;

    public class T02_EndToEndTest extends BaseTest {

        @Test(groups ="Purchase", dataProvider = "getData")
        public void full_Positive_Scenario_01(HashMap<String,String> input) {

            P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            productCatalogue.AddProductToCart(input.get("ProductName2"));
            P03_CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            P05_OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName2")));
            P06_ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }

        // add products to cart remove one of them and continue shopping
        @Test(dataProvider = "getData")
        public void full_Positive_Scenario_02(HashMap<String,String> input)
        {
            P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            productCatalogue.AddProductToCart(input.get("ProductName2"));
            productCatalogue.AddProductToCart(input.get("ProductName3"));
            P03_CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName3")));
            cartPage.RemoveItem(input.get("ProductName2"));
            Assert.assertFalse(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName3")));
            P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            P05_OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName3")));
            P06_ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }


        // Cancel Order and back home and full submit order
        @Test(dataProvider = "getData")
        public void full_Positive_Scenario_03(HashMap<String,String> input)
        {
            P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            P03_CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            String ReturnToCart = checkoutPage.Cancel();
            Assert.assertEquals(ReturnToCart,input.get("CartPageUrl"));
            cartPage.RemoveItem(input.get("ProductName"));
            cartPage.backToHomePage();
            productCatalogue.AddProductToCart(input.get("ProductName2"));
            productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName2")));
            checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            P05_OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName2")));
            P06_ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }

        @Test(dataProvider = "getData")
        public void full_Negative_scenario_01(HashMap<String,String> input)
        {
            P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            P03_CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("emptyFirstname"),input.get("emptyLastname"),input.get("emptyPostalCode"));
            checkoutPage.GoToCheckoutOverViewPage();
            Assert.assertEquals(checkoutPage.GetInformationErrorMessage(),input.get("errorMessage"));
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            P05_OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
            P06_ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }

        @Test(dataProvider = "getData",retryAnalyzer = RetryFailedTest.class)
        public void full_Negative_scenario_02_EmptyCart(HashMap<String,String> input)
        {
            P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("username"),input.get("password"));
            P03_CartPage cartPage =productCatalogue.GoToCart();
            cartPage.GoToCheckoutPage();
            Assert.assertEquals(cartPage.GetEmptyCartErrorMessage(),"Error : Empty Cart");
        }

        @Test(dataProvider = "getData")
        public void full_Negative_scenario_03(HashMap<String,String> input)
        {
            P02_ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("invalidUsername"),input.get("invalidPassword"));
            Assert.assertEquals(loginPage.GetLoginErrorMessage(),input.get("error-message"));
            loginPage.LoginApplication(input.get("username"),input.get("password"));
            productCatalogue.AddProductToCart(input.get("ProductName"));
            P03_CartPage cartPage =productCatalogue.GoToCart();
            Assert.assertTrue(cartPage.VerifyProductInTheCart(input.get("ProductName")));
            P04_CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
            checkoutPage.EnterInformation(input.get("firstname"),input.get("lastname"),input.get("PostalCode"));
            P05_OverViewPage overViewPage = checkoutPage.GoToCheckoutOverViewPage();;
            Assert.assertTrue(overViewPage.CheckProductIsDisplay(input.get("ProductName")));
            P06_ConfirmationPage confirmationPage = overViewPage.GoToConfirmationPage();
            Assert.assertEquals(confirmationPage.CheckConfirmationMessage(),input.get("confirmation-message"));
            Assert.assertEquals(confirmationPage.Logout(),input.get("landing-page-url"));
        }


        @DataProvider
        public Object[][] getData() throws IOException {
            List<HashMap<String,String>> data = getJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\BaseData.json");
            return new Object[][]{{data.get(0)}};
        }
    }





