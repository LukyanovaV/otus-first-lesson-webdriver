package tests;

import includes.LoginPage;
import includes.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest {

   public static WebDriver driver;

   public String login;
   public String password;

   @Parameters("browser")
   @BeforeMethod
   public void settingBrowser(@Optional("browser") String browser){

       WebDriverManager webDriverManager = new WebDriverManager(driver);
       driver = webDriverManager.getInstance(browser);

   }

   @Test
   public void loginPageTest(){
       LoginPage loginPage = new LoginPage(driver);
       loginPage.enterEmail("Wrong@mail.com");
       loginPage.enterPassword("12345679");
       Dimension beforeSize = loginPage.getSizeOfLoginBtn();
       loginPage.moveMouseToLoginBtn();
       Dimension afterSize = loginPage.getSizeOfLoginBtn();
       Assert.assertEquals(beforeSize, afterSize);
       Assert.assertEquals(loginPage.getColorOfLoginBtn(),"rgba(37, 121, 169, 1)");


   }

    @AfterMethod
    public void reset(){
        if(driver != null ) {
            driver.quit();
        }
    }






}
