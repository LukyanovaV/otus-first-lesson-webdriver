package includes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassengerData {
   // By inputName = By.xpath("//input[@name='inputName']");
    /*

                        WebElement inputNameOnCard = driver.findElement(By.xpath("//input[@name='nameOnCard']"));
                        WebElement inputFlight = driver.findElement(By.xpath("//input[@value='Purchase Flight']"));
                        */
    @FindBy(xpath = "//input[@name='inputName']")
    WebElement inputName;

    @FindBy(xpath="//input[@name='address']")
    WebElement inputAddress;

    @FindBy(xpath ="//input[@name='city']")
    WebElement inputCity;

    @FindBy(xpath ="//input[@name='state']")
    WebElement inputState;

    @FindBy(xpath="//input[@name='zipCode']")
    WebElement inputZipCode;

    @FindBy(xpath = "//select[@name='cardType']")
    WebElement inputCardType;

    @FindBy(xpath = "//input[@name='creditCardNumber']")
    WebElement inputCreditCardNum;

    @FindBy(xpath = "//input[@name='nameOnCard']")
    WebElement inputNameOnCard;

    @FindBy(xpath = "//input[@value='Purchase Flight']")
    WebElement buttonPurchFlight;


    private WebDriver driver;


    public PassengerData(WebDriver driver){
        this.driver = driver;

    }

    public void TypeInputs(String passengerName,
                           String address,
                           String city,
                           String state,
                           String zipCode,
                           String creditCardType,
                           String creditCardNum,
                           String nameOnCreditCard
                           ){
          inputName.clear();
          inputName.sendKeys(passengerName);
          inputAddress.clear();
          inputAddress.sendKeys(address);
          inputCity.clear();
          inputCity.sendKeys(city);
          inputState.clear();
          inputState.sendKeys(state);
          inputZipCode.clear();
          inputZipCode.sendKeys(zipCode);
          inputCardType.clear();
          inputCardType.sendKeys(creditCardType);
          inputCreditCardNum.clear();
          inputCreditCardNum.sendKeys(creditCardNum);
          inputNameOnCard.clear();
          inputNameOnCard.sendKeys(nameOnCreditCard);

          buttonPurchFlight.click();
    }

}
