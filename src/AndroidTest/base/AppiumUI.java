package AndroidTest.base;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class AppiumUI {

    static AndroidDriver driver;
    static Duration duration = Duration.ofSeconds(1);
    static Logger logger1 = Logger.getLogger("AppiumUI");
    private static final Log logger = LogFactory.get();

    public AppiumUI(AndroidDriver driver) {

        this.driver = driver;
    }

    /**
     * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
     */
    public static void waitForVisible(final By by, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        for (int attempt = 0; attempt < waitTime; attempt++) {
            try {
                driver.findElement(by);
                break;
            } catch (Exception e) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    /*
     *左滑
     *
     *
     */
    public void swipeLeft() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(PointOption.point(width - 10, height / 2)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width / 4, height / 2)).release();
        action1.perform();
    }

    /*
     *上滑
     *
     *
     */
    public void swipeUp() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(PointOption.point(width / 2, height / 2)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width / 2, (height / 2) - 300)).release();
        action1.perform();
    }

    /*
     *定位Element
     *
     * @param By by
     */
    public static WebElement element(By by) {
        waitForVisible(by, 5);
        WebElement element = driver.findElement(by);
        logger.info("打印状态：定位元素->" + by);
        return element;
    }

    /*
     *定位一组Element
     *
     * @param By by
     */
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    /*
     *判断元素是否存在
     *
     */
    public boolean isElementExist(By by) {
        logger.info("打印状态：定位元素->" + by);
        try {
            driver.findElement(by);
            logger.info("打印状态：元素->" + by + "已找到");
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /*
     * 封装click（点击）方法
     * 需要传入一个WebElement类型的元素
     *
     * */
    public static void click(WebElement webElement) {
        if (webElement != null) {
            webElement.click();
        } else {
            System.err.println("元素未定位到（" + webElement + "）,点击操作失败！！");
        }
    }

    /*
     *持续点击控件
     *
     */
    public void keepClickElement(By by) {
        try {
            WebElement webElement = driver.findElement(by);
            while (true) {
                if (webElement.isDisplayed()) {
                    webElement.click();
                } else {
                    break;
                }
            }
        } catch (NoSuchElementException e) {

        }
    }

    /*
     *出现阻塞步骤的系统弹窗时，accept 继续
     *
     */
    public void acceptPermission() {
        try {
            keepClickElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*允许.*\")"));
        } catch (Exception e) {

        }
//        keepClickElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*允许.*\")"));
    }

    /*
     * 判断元素是否显示方法
     *
     * */
    public boolean assertElementIs(WebElement element) {
        return element.isDisplayed();
    }

    /*
     * 封装getText（获取控件text属性）方法
     * 需要传入一个WebElement类型的元素
     *
     * */
    public String getText(WebElement webElement) {
        String test = webElement.getText();
        if (test != null) {
            System.out.println("获取当前元素TEXT属性成功!当前元素内输入内容为：" + test);
        } else {
            System.out.println("元素未定位到（" + webElement + "）,定位失败");
        }
        return test;
    }

    /*
     * 封装sendkeys（点击）方法
     * 需要传入一个WebElement类型的元素,一个String类型元素
     *
     * */
    public void sendKeys(WebElement webElement, String test) {
        if (webElement != null) {
            webElement.clear();
            try {
                webElement.click();
                webElement.sendKeys(test);
            } catch (Exception e) {

            }
            getText(webElement);
        } else {
            System.out.println("元素未定位到（" + webElement + "）,定位失败");
        }
    }

    /**
     * 滑动查找元素
     *
     * @throws InterruptedException
     */
    public void swipeFindElements(String sr) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement webElement = driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + sr + "\"))");
        //使用scrollIntoView方法实现滚动到指定控件元素
        System.err.println("已定位到元素：" + sr);
        webElement.click();
        System.err.println("已点击选择到元素：" + sr);
    }

    /*
     * 封装getToast方法
     * 需要传入一个By类型的元素
     *
     * */
    public String getToast(By by) {
        String text = null;
        String key = "验证码已成功";
        WebDriverWait wait = new WebDriverWait(driver, 0);
        // By.xpath("//*[@class='android.widget.Toast']")
        WebElement target = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'" + key + "')]")));
        text = target.getText();
        System.out.println(target.getText());

//        WebElement target = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if (target.getText() != null) {
//             text = target.getText();
            System.out.println(text);

        } else {
            System.out.println("没有获取到Toast的内容！！！");
        }
//        String text = target.getText();
        System.out.println(target.getText());
        return text;
    }

    public static String getSystemtime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        System.out.println(hehe);
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println(year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second);
        String time = year + "" + month + "" + date + "-" + hour + "-" + minute + "-" + second;
        return time;
    }

    /*
     * 封装截图方法
     * 需要传入一个String类型的名称
     *
     * */
    public void Screenshot(String filename) {
        File screen = driver.getScreenshotAs(OutputType.FILE);
        String name = getSystemtime() + filename;
        File screenFile = new File("d:\\TEST\\" + name);
        try {
            System.out.println("save snapshot path is:D:/TEST/" + name);
            FileUtils.copyFile(screen, screenFile);
        } catch (IOException e) {
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        } finally {
            System.out.println("screen shot finished");
        }

    }

    /**
     * 控制滑动方向
     */
    public static enum Heading {
        UP, DOWN
    }

    /**
     * 控件内上下滑动
     * 测试步骤
     * 控件定位方式
     */
    public static void swipeControl(WebElement element, Heading heading){
        // 获取控件开始位置的坐标轴
        Point start = element.getLocation();
        int startX = start.x;
        int startY = start.y;
        // 获取控件坐标轴差
        Dimension q = element.getSize();
        int x = q.getWidth();
        int y = q.getHeight();
        // 计算出控件结束坐标
        int endX = x + startX;
        int endY = y + startY;
        // 计算中间点坐标
        int centreX = (endX + startX) / 2;
        int centreY = (endY + startY) / 2;
        switch (heading) {
            // 向上滑动
            case UP:
//                driver.qaswipeUp(centreX, centreY + 30, centreX, centreY - 30);
                TouchAction action1 = new TouchAction(driver).press(PointOption.point(centreX, centreY + 300)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(centreX, endY - 700)).release();
                action1.perform();
                System.out.println("=========滑动操作成功===================");
                break;
            // 向下滑动
//            case DOWN:
//                driver.qaswipeUp(centreX, centreY - 30, centreX, centreY + 30, 500);
//                break;

        }
    }

//        /**
//         * 下拉框随机下滑选择
//         * */
//        public static void RandomSwipe (int number, WebElement element){
//            int j = PagesAppium.RandomNum(number);
//            System.err.println("随机选项为：" + j);
//            if (j > 0) {
//                int i = 1;
//                while (i <= j) {
//                    swipeControl(element, Heading.UP);
//                    i++;
//                }
//            }
//        }


}







