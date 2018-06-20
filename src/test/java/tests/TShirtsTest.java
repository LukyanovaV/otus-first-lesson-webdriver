package tests;

import includes.WebDriverLogger;
import includes.WebDriverManager;
import objects.HomePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class TShirtsTest {

   public WebDriver driver;
   public EventFiringWebDriver event;
   public WebDriverManager webDriverManager;

    @Parameters("browser")
    @BeforeTest
    public void settingBrowser(@Optional("browser") String browser){

        webDriverManager = new WebDriverManager(driver);
        driver = webDriverManager.getInstance(browser);
        event = new EventFiringWebDriver(driver);
        event.register(new WebDriverLogger());

    }

    @Test
    public void tShirtsTest(){
        HomePage homePage = new HomePage(event);
        homePage.chooseTshirtsMenu();

    }

    @Parameters("browser")
    @AfterMethod
    public void reset(ITestResult result, @Optional("browser") String browser){

        if(!result.isSuccess()){
            File scr =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scr, new File("log/screenshot.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(browser.equals("Chrome_to_HAR")) {
            try {
                webDriverManager.writeToHar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
