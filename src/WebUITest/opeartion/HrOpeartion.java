package WebUITest.opeartion;

import WebUITest.base.WebUI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HrOpeartion extends WebUI {

    private final static Logger Log = Logger.getLogger(HrOpeartion.class);

    public HrOpeartion(WebDriver driver) {
        super(driver);
    }

    public void inputLoginName() {
        WebElement element = getElement("loginName");
        element.clear();
        element.sendKeys("8901000007");
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
        Log.info("第四步：点击面和面签操作");
    }

    public void inputSearchPhone() {
        WebElement element = getElement("searchInp");
        element.clear();
        element.sendKeys("18611065983");
        element.sendKeys(Keys.ENTER);
        Log.info("第五步：输入搜索手机号码，点击enter键进行搜索操作");
    }

    public void clicksignforBtn() {

        if (isElementExist(getBy("cancelBtn"))) {
            WebElement element = getElement("cancelBtn");
            element.click();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = getElement("signforBtn");
        element.click();
        Log.info("第六步：签收操作");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickdisposeBtn() {
        WebElement element = getElement("disposeBtn");
        if(element.isDisplayed()){
            element.click();
            Log.info("第七步：处理操作");
        }else {
            Log.info("=========元素失效了====================");
        }
//        element.click();

    }

}
