package tests;

import includes.WebDriverManager;
import objects.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TShirtsTest {

   public WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void settingBrowser(@Optional("browser") String browser){

        WebDriverManager webDriverManager = new WebDriverManager(driver);
        driver = webDriverManager.getInstance(browser);

    }

    @Test
    public void tShirtsTest(){
        HomePage homePage = new HomePage(driver);
        homePage.chooseTshirtsMenu();
        Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?id_category=5&controller=category");


    }

    @AfterMethod
    public void reset(){
            driver.quit();
    }
}
