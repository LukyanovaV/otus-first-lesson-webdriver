package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "//li/a[@title='Women']")
    WebElement womenMenu;

    private static By tshirtsMenuAdress = By.xpath("//li/a[@title='T-shirts']");

    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get("http://automationpractice.com/index.php");

        PageFactory.initElements(driver, this);

    }

    public void chooseTshirtsMenu(){
        Actions actions = new Actions(driver);

        actions.moveToElement(womenMenu).build().perform();
        WebElement tshirtsMenu =(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(tshirtsMenuAdress));
        tshirtsMenu.click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?id_category=5&controller=category");

    }



}
