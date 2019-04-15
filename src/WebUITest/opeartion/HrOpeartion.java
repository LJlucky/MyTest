package WebUITest.opeartion;

import WebUITest.base.WebUI;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
        element.sendKeys("8901000016");
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
        element.sendKeys("13966650021");
        element.sendKeys(Keys.ENTER);
        Log.info("第五步：输入搜索手机号码，点击enter键进行搜索操作");
    }

    public void clicksignforBtn() {

        if (isElementExist(getBy("cancelBtn"))) {
            Log.info("进件已签收，取消后重新签收");
            WebElement element = getElement("cancelBtn");
            element.click();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = getElement("signforBtn");
        element.click();
        Log.info("第六步：签收操作");

    }

    public void clickdisposeBtn() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = getElement("disposeBtn");
        if (element.isDisplayed()) {
            element.click();
            Log.info("第七步：处理操作");
        } else {
            Log.info("=========元素失效了====================");
        }
    }

    public void clickNextStepBtn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = getElement("nextstepBtn");
        WebElement element2 = getElement("title");
        String title = element2.getText();
        Log.info("当前页的title是：" + title);
        swip(element);
        click(element);
        Log.info(title + "：点击“下一步”操作");
    }

    public void chooseLoanInformation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element1 = getElement("productType");
        WebElement element2 = getElement("repayType");
        element1.click();
//        js.executeScript("");
        String text2 = element2.getText();
        Log.info(text2);
    }

}
