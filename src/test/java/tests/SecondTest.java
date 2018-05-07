package tests;

import includes.PassengerData;
import includes.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SecondTest {

             WebDriver driver;


            @Parameters({"browser"})
            @BeforeMethod
            public void SettingBrowser(String browser){

                WebDriverManager webDriverManager = new WebDriverManager();
                webDriverManager.GetBrowser(browser);
                driver = webDriverManager.getInstance();
                driver.get("http://blazedemo.com/index.php");

            }


            @AfterSuite
            public void reset(){
                if(driver != null ) {
                    driver.quit();
                }
           }


            @Test
            public void checkingCities(){


                String departsWord;
                String arriveWord;


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



            @Test
            public void checkBayTicket(){


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
                        PassengerData passengerData = new PassengerData(driver);
                        passengerData.TypeInputs("Name", "Street", "City", "State", "1233456789",  "122334450", "NameCard");



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


                    }





                }



