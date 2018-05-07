package includes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;

            default:
               // throw new IllegalStateException("Browser isn't correct!");
                driver = new ChromeDriver();
        }
        return driver;
    }

}
