package WebUITest.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import java.io.FileNotFoundException;
import java.util.Map;

public class WebUI {

    public WebDriver driver;
    public Common common;
    public void startTest(){
        String objPath = System.getProperty("user.dir") + "\\src\\WebUITest\\Driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",objPath);
//        System.setProperty("webdriver.chrome.driver","D:\\idea-workspace\\TestDemo\\src\\WebUITest\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
        driver.quit();
    }

    //创建by对象
    private By getBy(String key) throws FileNotFoundException {
        By by = null;
        Map<String, Map<String, String>> ml = null;
//        Map<String, String> ml = null;

            ml = Common.LoadYaml(key);

        if (ml.containsKey(key)){
            Map<String,String> m = ml.get(key);
            String type = m.get("type");
            String value = m.get("value");
            switch(type){
                case "id":
                    by = By.id(value);
                    break;
                case "name":
                    by = By.name(value);
                    break;
                case "xpath":
                    by = By.xpath(value);
                    break;
                case "class":
                    by = By.className(value);
                    break;
                case "linkText":
                    by = By.linkText(value);
                    break;
                case "cssSelector":
                    by= By.cssSelector(value);
                    break;
            }
        }
        else
        {
            System.out.println("Locator "+key+" is not exist in  ");
        }
        return by;
    }
    //根据名称 返回webElement对象
    public WebElement getElement(String key) throws FileNotFoundException {
        By by = this.getBy(key);
        WebElement  element = driver.findElement(by);
        System.out.println(element);
        return element;
    }
}
