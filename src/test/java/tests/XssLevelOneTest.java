package tests;

import includes.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class XssLevelOneTest {

    WebDriver driver;
    WebDriverManager webDriverManager;

    @DataProvider(name = "XSS_alert")
    public static Object[] testingData(){

        return new Object[]{"<script>alert(\"1\");</script>"};
    }


    @Parameters("browser")
    @BeforeTest
    public void setUp(@Optional("browser") String browser) {
        webDriverManager = new WebDriverManager(driver);
        driver = webDriverManager.getInstance(browser);
    }

    @Test(dataProvider = "XSS_alert" )
    public void xssShoudNotWork(String xss) {
        driver.get("http://www.xss-game.appspot.com/level1");
        WebElement element = driver.findElement(By.cssSelector("iframe.game-frame"));

        driver.switchTo().frame(element);
        driver.findElement(By.id("query")).sendKeys(xss);
        driver.findElement(By.id("button")).click();


        Assert.assertFalse(webDriverManager.isAlertPresent());
    }

    @AfterMethod
    public void reset(){
        driver.quit();
    }

}


