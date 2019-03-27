package WebUITest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public class InitWeb {

    public WebDriver driver;

    @BeforeSuite
    public void startTest() {
        String url = "https://www.baidu.com";
        System.setProperty("webdriver.chrome.driver", "D:\\idea-workspace\\TestDemo\\src\\WebUITest\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.quit();
    }

    public WebDriver getDriver() {

        return driver;
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }

}
