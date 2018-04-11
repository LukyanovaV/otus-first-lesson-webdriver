package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SecondTest {

            WebDriver driver;

           public enum Browsers {
               chrome,
               firefox
           }


            public void SettingBrowser(Browsers browser){

                switch(browser){
                    case chrome:
                        System.setProperty("webdriver.chrome.driver", "lib\\chrome\\chromedriver.exe");
                        driver = new ChromeDriver();
                        break;
                    case firefox:
                        System.setProperty("webdriver.gecko.driver", "lib\\firefox\\geckodriver.exe");
                        driver = new FirefoxDriver();
                        break;

                    default:
                }

            }

            @BeforeClass
            public void precondition(){

            }

            @BeforeTest
            public void initialization(){
               //System.setProperty("webdriver.chrome.driver", "lib\\chrome\\chromedriver.exe");
                // driver = new ChromeDriver();

            }

            @AfterTest
            public void reset(){
   //             if(driver.getWindowHandles().size()>1) {
   //                 driver.quit();
   //             }else {
   //                 driver.close();
   //             }
           }


            @Test
            public void checkingCities(){

                String departsWord;
                String arriveWord;

                for(Browsers browser : Browsers.values()){
                    SettingBrowser(browser);

                    driver.get("http://blazedemo.com/index.php");
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                    int i = 1;
                    for(;;) {

                        try {
                            WebElement depCity = driver.findElement(By.xpath("//select[1]/option[" + i + "]"));
                        }catch(NoSuchElementException e){
                            break;
                        }
                             int j =1;
                               for(;;){


                                try {
                                    WebElement depCity = driver.findElement(By.xpath("//select[1]/option[" + i + "]"));
                                    WebElement findFlightsButton = driver.findElement(By.xpath("//input"));
                                    WebElement desCity = driver.findElement(By.xpath("//select[2]/option["+j+"]"));


                                    desCity.click();
                                    depCity.click();

                                    departsWord = depCity.getText();
                                    arriveWord = desCity.getText();
                                    findFlightsButton.click();

                                    WebElement departsTable = driver.findElement(By.cssSelector("body > div.container > table > thead > tr > th:nth-child(4)"));
                                    WebElement arrivesTable = driver.findElement(By.cssSelector("body > div.container > table > thead > tr > th:nth-child(5)"));


                                    Assert.assertEquals("Departs: "+departsWord,departsTable.getAttribute("textContent"));
                                    Assert.assertEquals("Arrives: "+arriveWord,arrivesTable.getAttribute("textContent"));

                                    j++;
                                    driver.get("http://blazedemo.com/index.php");

                                }catch (NoSuchElementException e){
                                    break;
                                }
                            }
                            i++;
                    }
                    driver.quit();

                }

            }

            @Test
            public void checkBayTicket(){

                for(Browsers browser : Browsers.values()) {

                    SettingBrowser(browser);

                    driver.get("http://blazedemo.com/index.php");

                    WebElement depCity = driver.findElement(By.xpath("//select[1]/option[4]"));
                    WebElement findFlightsButton = driver.findElement(By.xpath("//div[3]/form/div/input"));
                    WebElement desCity = driver.findElement(By.xpath("//select[2]/option[3]"));

                    desCity.click();
                    depCity.click();
                    findFlightsButton.click();

                    List<WebElement> chooseFlightButtons = driver.findElements(By.cssSelector("button.btn.btn-small, input[type=\"submit\"].btn.btn-small"));
                    ArrayList<String> prices = new ArrayList<String>();

                    for(int j=1; j<=chooseFlightButtons.size(); j++){
                        if(chooseFlightButtons.get(j-1).getAttribute("value").equals("Choose This Flight")) {
                            WebElement price = driver.findElement(By.xpath("//tr[" + j + "]/input[2]"));
                            prices.add(j-1, price.getAttribute("value"));

                        }
                        else { continue;}
                    }

                    for(int i=1; i<=chooseFlightButtons.size();i++) {
                        WebElement chooseFlightButton =(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//tr["+i+"]/td[1]/input")));
                        chooseFlightButton.click();

                        //Проверка стоимости
                        WebElement price = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()[contains(., 'Price')]]")));
                        WebElement fees = driver.findElement(By.xpath("//p[text()[contains(., 'Arbitrary Fees and Taxes')]]"));
                        WebElement totalCost = driver.findElement(By.xpath("//p[text()[contains(., 'Total Cost')]]/em"));

                        Assert.assertEquals("Price: "+prices.get(i-1), price.getText());

                        if(price.getText().equals("Price: "+prices.get(i-1))){
                            String strFees = fees.getText().substring(fees.getText().indexOf(":")+1);
                            String strTotalCost = totalCost.getText().substring(totalCost.getText().indexOf(":")+1);
                            float intTotalCost= Float.parseFloat(strTotalCost);
                            float intFees = Float.parseFloat(strFees);
                            float intPrice = Float.parseFloat(prices.get(i-1));

                            //округление числа до двух знаков
                            String sum = String.format("%.2f",intFees+intPrice);
                            sum = sum.replace(',','.');
                            float sumTotalCost = Float.parseFloat(sum);

                            Assert.assertEquals(intTotalCost, sumTotalCost);
                         }

                        //Заполнение формы
                        WebElement inputName = driver.findElement(By.xpath("//input[@name='inputName']"));
                        WebElement inputAddress = driver.findElement(By.xpath("//input[@name='address']"));
                        WebElement inputCity = driver.findElement(By.xpath("//input[@name='city']"));
                        WebElement inputState = driver.findElement(By.xpath("//input[@name='state']"));
                        WebElement inputZipCode = driver.findElement(By.xpath("//input[@name='zipCode']"));
                        WebElement inputCardType = driver.findElement(By.xpath("//select[@name='cardType']"));
                        WebElement inputCardNumber = driver.findElement(By.xpath("//input[@name='creditCardNumber']"));
                        WebElement inputNameOnCard = driver.findElement(By.xpath("//input[@name='nameOnCard']"));
                        WebElement inputFlight = driver.findElement(By.xpath("//input[@value='Purchase Flight']"));

                        inputName.clear();
                        inputName.sendKeys("11232343**");
                        inputAddress.clear();
                        inputAddress.sendKeys("+++++");
                        inputCity.clear();
                        inputCity.sendKeys("--------");
                        inputState.clear();
                        inputState.sendKeys("!!!!!!");
                        inputZipCode.clear();
                        inputZipCode.sendKeys("000000");
                        inputCardNumber.clear();
                        inputCardNumber.sendKeys("00000000");
                        inputNameOnCard.clear();
                        inputNameOnCard.sendKeys("---------");

                        inputFlight.click();

                        WebElement id = driver.findElement(By.xpath("//tr[1]/td[2]"));
                        WebElement status = driver.findElement(By.xpath("//tr[2]/td[2]"));
                        WebElement amount = driver.findElement(By.xpath("//tr[3]/td[2]"));
                        WebElement date = driver.findElement(By.xpath("//tr[3]/td[2]"));

                        Assert.assertNotEquals(id.getText(), "");
                        Assert.assertNotEquals(status.getText(), "");
                        Assert.assertNotEquals(amount.getText(), "");
                        Assert.assertNotEquals(date.getText(), "");

                        driver.navigate().back();
                        driver.navigate().back();
                        }

                    driver.quit();
                    }





                }

            }

