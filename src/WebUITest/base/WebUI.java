package WebUITest.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileNotFoundException;
import java.util.Map;

public class WebUI {
    private final static Logger Log = Logger.getLogger(WebUI.class);
    public WebDriver driver;
    public JavascriptExecutor js = (JavascriptExecutor) driver;

    Map<String, Map<String, String>> ml;

    {
        try {
            ml = Common.getYaml();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
    public By getBy(String key) {
        By by = null;
//        Map<String, Map<String, String>> ml = null;
//        Map<String, String> ml = null;

//        ml = Common.getYaml();

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
    public boolean isElementExist(By by) {
//        Log.info("开始查找元" + by);
        try {
            driver.findElement(by);
//            Log.info("已找到元素" + by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    //规定时间内等待元素
    protected void waitForElement(By by) {
        for (int second = 0; ; second++) {
            if (second > 20) {
                Log.info(by + "元素加载失败");
            }
            try {
                if (isElementExist(by)) {
//                    Log.info("元素存在===" + by + ">>>>>" + second);
                    break;
                }
            } catch (Exception e) {
                Log.info("在20秒内未找到元素" + by);
            }
        }
    }


    //根据名称 返回webElement对象
    public WebElement getElement(String key) {
        WebElement element = null;
        By by = this.getBy(key);
//        if (isElementExist(by)) {
//            element = driver.findElement(by);
////            Log.info("测试是不是获取成功" + element);
////            System.out.println("测试是不是获取成功" + element);
//        }else{
//            Log.info("获取" + element + "失败！！！");
//        }
        waitForElement(by);
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        wait.until(ExpectedConditions.presenceOfElementLocated(by));
//        final List<WebElement> until = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by);
        element = driver.findElement(by);
        return element;
    }


    //根据element,进行点击操作
    public void click(WebElement element) {
        element.click();
    }

    //根据element,进行滑动操作
    public void swip(WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) driver;

        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * 每隔一秒check一下页面加载是否完成，check次数是25
     */
    public void checkPageIsReady(JavascriptExecutor js) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 25; i++) {
            if ("complete".equals(js.executeScript("return document.readyState").toString())) {
                Log.info("加载完毕。。。。。。。");
                break;
            }
            try {
                Log.info("未加载完毕，等待一秒。。。。。。。");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
