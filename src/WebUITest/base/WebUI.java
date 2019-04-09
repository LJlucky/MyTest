package WebUITest.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.util.Map;

public class WebUI {
    private final static Logger Log = Logger.getLogger(WebUI.class);
    public WebDriver driver;


    public WebUI(WebDriver driver) {
        this.driver = driver;
    }

    public void startTest() {
        String objPath = System.getProperty("user.dir") + "\\src\\WebUITest\\Driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", objPath);
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

        if (ml.containsKey(key)) {
            Map<String, String> m = ml.get(key);
            String type = m.get("type");
            String value = m.get("value");
            switch (type) {
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
                    by = By.cssSelector(value);
                    break;
            }
        } else {
            System.out.println("Locator " + key + " is not exist in  ");
        }
        return by;
    }

    /*
     *判断元素是否存在
     *
     */
    private boolean isElementExist(By by) {
        Log.info("开始查找元" + by);
        try {
            driver.findElement(by);
            Log.info("已找到元素" + by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    //根据名称 返回webElement对象
    public WebElement getElement(String key) throws FileNotFoundException {
        WebElement element = null;
        By by = this.getBy(key);
        if (isElementExist(by)) {
            element = driver.findElement(by);
            Log.info("测试是不是获取成功" + element);
            System.out.println("测试是不是获取成功" + element);
        }else{
            Log.info("获取" + element + "失败！！！");
        }
        return element;
    }

    //根据element,进行点击操作
    public void click(WebElement element) {
        element.click();
    }

}
