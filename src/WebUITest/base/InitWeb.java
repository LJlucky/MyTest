package WebUITest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitWeb {

    private WebDriver driver;

    @BeforeSuite
    private void startTest() {
        String url = "https://www.baidu.com";
        System.setProperty("webdriver.chrome.driver", "D:\\idea-workspace\\TestDemo\\src\\WebUITest\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }

}
