package WebUITest.opeartion;

import WebUITest.base.WebUI;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

public class HrOpeartion extends WebUI {

    private final static Logger Log = Logger.getLogger(HrOpeartion.class);

    public HrOpeartion(WebDriver driver) {
        super(driver);
    }

    public void inputLoginName() {
        WebElement element = getElement("loginName");
        element.clear();
        element.sendKeys("8901000006");
        Log.info("第一步：输入登录账户");
    }

    public void inputLoginPwd() {
        WebElement element = getElement("loginPwd");
        element.clear();
        element.sendKeys("a1111112");
        Log.info("第二步：输入登录密码");
    }

    public void clickLogin() {
        WebElement element = getElement("loginBtn");
        element.click();
        Log.info("第三步：点击登录操作");
    }

    public void clickMain() {
        WebElement element = getElement("mainBtn");
        element.click();
        Log.info("第四步：点击面和面签");
    }


}
