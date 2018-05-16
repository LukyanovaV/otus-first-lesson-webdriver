package includes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private static String browser;
    private static WebDriver driver;

    public static String nodeURL = "http://172.20.1.75:3278/wd/hub";


    public WebDriverManager(WebDriver driver){
        this.driver = driver;

    }

    public WebDriver getInstance(String browser){
        this.browser = browser;
        if(driver == null){

        driver = WebDriverStart();
    }
    return driver;
}


    private static   WebDriver WebDriverStart() {
        switch (browser) {
            case "Chrome-headless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "Chrome-remote":
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), DesiredCapabilities.chrome());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox-remote":
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), DesiredCapabilities.firefox());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IExplorer":
               // driver = new InternetExplorerDriver();
                break;
            default:
               driver = new ChromeDriver();

        }


        return driver;
    }

}
