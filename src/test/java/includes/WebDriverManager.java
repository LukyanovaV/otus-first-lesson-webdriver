package includes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverManager {
    private static String browser;
    private static WebDriver driver;

    public static WebDriver getInstance(){
        if(driver == null){
        driver = WebDriverStart();
    }
    return driver;
}

    public String GetBrowser(String browser){
        this.browser = browser;
        return this.browser;
    }

    private static   WebDriver WebDriverStart() {
        switch (browser) {
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IExplorer":
                driver = new InternetExplorerDriver();
                break;
            default:
               // throw new IllegalStateException("Browser isn't correct!");
                driver = new InternetExplorerDriver();

        }
        return driver;
    }

}
