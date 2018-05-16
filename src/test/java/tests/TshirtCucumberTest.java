package tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TshirtCucumberTest {

    public WebDriver driver;


    @Given("^I am on home page$")
    public void i_am_on_home_page(){
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");

    }


    @When("^I move mouse to Women button and choose Tshirts button from menu$")
    public void choose_tshirts_button_from_menu(){
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//li/a[@title='Women']"))).build().perform();
        WebElement tshirtsMenu =(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//li/a[@title='T-shirts']")));
        tshirtsMenu.click();
    }

    @Then("^I am on Tshirts page$")
    public void i_am_on_tShirt_page(){
        Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?id_category=5&controller=category");
    }

    @AfterTest
    public void close_driver(){
        driver.quit();
    }

}
