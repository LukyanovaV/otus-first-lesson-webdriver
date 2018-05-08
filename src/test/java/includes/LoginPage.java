package includes;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//input[@id='email']")
    WebElement eMailInput;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement loginBtn;

    @FindBy(xpath = "//input[@name='remember']")
    WebElement rememberMeBtn;

    @FindBy(xpath = "//span[@class='help-block']")
    WebElement helpMessage;

    private WebDriver driver;

            public LoginPage(WebDriver driver){

                this.driver =driver;
                driver.get("http://blazedemo.com/login");
                PageFactory.initElements(driver, this);

            }

            public LoginPage enterEmail(String email){
                eMailInput.clear();
                eMailInput.sendKeys(email);
                return this;
            }

            public LoginPage enterPassword(String password){
                passwordInput.clear();
                passwordInput.sendKeys(password);
                return this;
            }

            public void moveMouseToLoginBtn(){
                Actions actions = new Actions(driver);
                actions.moveToElement(loginBtn).build().perform();
            }

            public Dimension getSizeOfLoginBtn(){
                return loginBtn.getSize();
            }

            public String getColorOfLoginBtn(){
                String colorOfLoginBtn =loginBtn.getCssValue("background-color");
                return colorOfLoginBtn;
            }


            public void clickRememberBtn(){
                rememberMeBtn.click();
            }

            public void clickLoginBtn(){
                loginBtn.click();
            }

            public String wrongMessage(){

                return helpMessage.getText();
            }

}
