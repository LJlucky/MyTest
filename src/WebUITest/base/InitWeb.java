package WebUITest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitWeb {

    private WebDriver driver;

    @BeforeSuite
    public void startTest() {
        String url = "https://www.baidu.com";
        String objPath = System.getProperty("user.dir")+"\\src\\WebUITest\\Driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",objPath);
        driver = new ChromeDriver();
        driver.get(url);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }

}
