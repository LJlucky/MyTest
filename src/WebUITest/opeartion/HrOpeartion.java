package WebUITest.opeartion;

import WebUITest.base.WebUI;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;


public class HrOpeartion extends WebUI {

    private final static Logger Log = Logger.getLogger(HrOpeartion.class);

    public HrOpeartion(WebDriver driver) {
        super(driver);
    }

    //第一步：输入登录账户
    public void inputLoginName() {
        WebElement element = getElement("loginName");
        element.clear();
        element.sendKeys("8901000016");
        Log.info("第一步：输入登录账户");
    }

    //第二步：输入登录密码
    public void inputLoginPwd() {
        WebElement element = getElement("loginPwd");
        element.clear();
        element.sendKeys("a1111112");
        Log.info("第二步：输入登录密码");
    }

    //第三步：点击登录操作
    public void clickLogin() {
        WebElement element = getElement("loginBtn");
        element.click();
        Log.info("第三步：点击登录操作");
    }

    //第四步：点击面和面签操作
    public void clickMain() {
        WebElement element = getElement("mainBtn");
        element.click();
        Log.info("第四步：点击面和面签操作");
        if (isElementExist(getBy("cancelBtn"))) {
            Log.info("进件已签收，取消后重新签收");
            WebElement element1 = getElement("cancelBtn");
            element1.click();
        }
    }

    //第五步：输入搜索手机号码，点击enter键进行搜索操作
    public void inputSearchPhone() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = getElement("searchInp");
        element.clear();
        element.sendKeys("13288951038");
        element.sendKeys(Keys.ENTER);
        Log.info("第五步：输入搜索手机号码，点击enter键进行搜索操作");
    }

    //第六步：签收操作
    public void clickSignforBtn() {
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

    //第七步：处理操作
    public void clickDisposeBtn() {
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

    //点击“下一步”操作
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

    //选择贷款产品
    public void chooseLoanInformation() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String productType = null;
        String repayMethod = null;
        String loanPeriod = null;
        String loanTerm = null;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("complete".equals(js.executeScript("return document.readyState").toString())) {
            repayMethod = (String) js.executeScript("var input = document.querySelector('#repayMethod').value; return input;");
            loanPeriod = (String) js.executeScript("var input = document.querySelector('#loanProductType').value;return input;");
            productType = (String) js.executeScript("var input = document.querySelector('#productType').value; return input;");
            loanTerm = (String)js.executeScript("var input = document.querySelector('#loanTerm').value; return input;");

            Log.info("还款方式" + repayMethod + "产品系列：" + loanPeriod + "贷款产品：" + repayMethod + "贷款期限：" + loanTerm);
        } else {
            Log.info("没有获取到值=====");
        }

        if (productType != null) {
            if (repayMethod.equals("等额本息")) {
                Log.info("当前已选择的还款方式为：" + repayMethod);
            } else {
                WebElement element = getElement("repayMethod");
                click(element);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement element1 = getElement("chooseRepayMethod");
                click(element1);
            }

        }

        if (repayMethod != null) {
            if (loanPeriod.equals("有房贷")) {
                Log.info("当前已选择的产品系列：" + loanPeriod);
            } else {
                WebElement element = getElement("loanPeriod");
//                click(element);
                element.click();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement element1 = getElement("chooseLoanPeriod");
                click(element1);
            }
        }

        if (loanPeriod != null) {
            if (loanTerm.equals("6期")) {
                Log.info("当前已选择的贷款期数是：" + loanPeriod);
            } else {
                WebElement element = getElement("loanTerm");
//                click(element);
                element.click();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement element1 = getElement("chooseLoanTerm");
                click(element1);
            }
        }


    }
}
