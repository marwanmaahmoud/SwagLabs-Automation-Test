package SwagLabsDemo.TestComponants;

import SwagLabsDemo.PageObjects.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public P01_LoginPage loginPage;
    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
                "/src/main/java/SwagLabsDemo/Resources/GlobalData.properties");
        properties.load(file);
        String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");
        if(BrowserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        } else if (BrowserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (BrowserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
        } else if (BrowserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        return driver;
    }
    public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"\\reports\\reports"+ TestCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"\\reports\\reports"+ TestCaseName + ".png";
    }


    public List<HashMap<String, String>> getJsonDataHashmap(String filePath) throws IOException {
        String JsonConten = FileUtils.readFileToString((new File(filePath)
        ));
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(JsonConten, new TypeReference<List<HashMap<String,String>>>() {});
        return data;
    }


    @BeforeMethod
    public P01_LoginPage launchWebsite() throws IOException {
        driver = initializeDriver();
        loginPage = new P01_LoginPage(driver);
        loginPage.goTo();
        return loginPage;
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
