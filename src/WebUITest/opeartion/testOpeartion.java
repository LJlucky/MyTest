package WebUITest.opeartion;

import WebUITest.base.WebUI;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

public class testOpeartion extends WebUI {
    private final static Logger Log = Logger.getLogger(testOpeartion.class);
    public testOpeartion(WebDriver driver) {
        super(driver);
    }

    public void testBaiduClick() throws FileNotFoundException {
        click(getElement("baidu1"));
    }

    public void inputLoginName() throws FileNotFoundException {
        WebElement element = getElement("loginName");
        element.clear();
        element.sendKeys("8901000006");
        Log.info("第一步：输入登录账户");
    }

    public void inputLoginPwd() throws FileNotFoundException {
        WebElement element = getElement("loginPwd");
        element.clear();
        element.sendKeys("a1111112");
        Log.info("第二步：输入登录密码");
    }

    public void clickLogin() throws FileNotFoundException {
        WebElement element = getElement("loginBtn");
        element.click();
        Log.info("第三步：点击登录操作");
    }

}
