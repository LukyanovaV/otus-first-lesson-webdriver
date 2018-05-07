package tests;

import includes.LoginPage;
import includes.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTest {

   public static WebDriver driver;

   public String login;
   public String password;

   @Parameters("browser")
   @BeforeMethod
   public void settingBrowser(@Optional("browser") String browser){

       WebDriverManager webDriverManager = new WebDriverManager();
       webDriverManager.GetBrowser(browser);
       driver = webDriverManager.getInstance();

   }

   @Parameters({"login","password"})
   @Test
   public void loginPageTest(){
       LoginPage loginPage = new LoginPage(driver);
       loginPage.enterEmail(login);
       loginPage.enterPassword(password);
       loginPage.clickLoginBtn();
       Assert.assertEquals(loginPage.wrongMessage(),"These credentials do not match our records.");

   }








}
