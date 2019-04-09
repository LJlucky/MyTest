package WebUITest.testDemo;

import WebUITest.base.Common;
import WebUITest.base.InitWeb;
import WebUITest.opeartion.testOpeartion;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class login extends InitWeb {


    private final static Logger Log = Logger.getLogger(login.class);
    private WebDriver driver;
    public testOpeartion a;
    @BeforeClass
    public void setDriver() {
        driver = getDriver();
        a = new testOpeartion(driver);
//        try {
//            Common.LoadYaml("hrweixin.yaml");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void test001() throws FileNotFoundException {
        Log.info("==============开始登录测试001===================");
        if (driver != null) {
            System.out.println("启动成功！！");
//            a.getElement("baidu1");

            a.inputLoginName();
            a.inputLoginPwd();
            a.clickLogin();
        } else {
            System.out.println("启动失败！！");
        }
    }
}
