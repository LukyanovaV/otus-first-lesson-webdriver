package tests;

import includes.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class XssLevelOneTest {

    public static String XSS_QUERY = "?<script>alert(\"1\");</script>";
    WebDriver driver;
    WebDriverManager webDriverManager;


    @Parameters("browser")
    @BeforeTest
    public void setUp(@Optional("browser") String browser) {
        webDriverManager = new WebDriverManager(driver);
        driver = webDriverManager.getInstance(browser);
    }

    @Test
    public void xssShoudNotWork() {
        driver.get("http://www.xss-game.appspot.com/level1");
        WebElement element = driver.findElement(By.cssSelector("iframe.game-frame"));

        driver.switchTo().frame(element);
        driver.findElement(By.id("query")).sendKeys(XSS_QUERY);
        driver.findElement(By.id("button")).click();


        Assert.assertFalse(webDriverManager.isAlertPresent());
    }

    @AfterMethod
    public void reset(){
        driver.quit();
    }

}


