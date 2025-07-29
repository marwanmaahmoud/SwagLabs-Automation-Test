    package SwagLabsDemo.Tests;

    import SwagLabsDemo.TestComponants.BaseTest;
    import SwagLabsDemo.TestComponants.RetryFailedTest;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;

    import java.io.IOException;
    import java.util.HashMap;
    import java.util.List;

    public class LoginErrorValidationsTest extends BaseTest {

        @Test(dataProvider = "GetData",retryAnalyzer = RetryFailedTest.class)
        public void LoginErrorValidation(HashMap<String,String> input)
        {
            loginPage.LoginApplication(input.get("username"),input.get("password"));
            Assert.assertEquals(loginPage.GetLoginErrorMessage(),input.get("error-message"));
        }

        @DataProvider
        public Object[][] GetData() throws IOException {
            List<HashMap<String,String>> data = GetJsonDataHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\SwagLabsDemo\\Resources\\LoginErrorValidationsData.json");
            return new Object[][]{{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
        }
    }
