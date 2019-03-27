package WebUITest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebUI {

    public WebDriver driver;

    public void startTest(){
        String objPath = System.getProperty("user.dir") + "\\src\\WebUITest\\Driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",objPath);
//        System.setProperty("webdriver.chrome.driver","D:\\idea-workspace\\TestDemo\\src\\WebUITest\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
        driver.quit();
    }
}
