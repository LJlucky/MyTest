package WebUITest.testDemo;

import WebUITest.base.InitWeb;
import WebUITest.opeartion.testOpeartion;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class startWeb extends InitWeb {


    private WebDriver driver;
    public testOpeartion a;
    @BeforeClass
    public void setDriver() {

        driver = getDriver();
         a = new testOpeartion(driver);
    }

    @Test
    public void test001() throws FileNotFoundException {
        if (driver != null) {
            System.out.println("启动成功！！");
//            a.getElement("baidu1");
            a.testBaiduClick();
        } else {
            System.out.println("启动失败！！");
        }
    }

}
