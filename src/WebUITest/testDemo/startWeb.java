package WebUITest.testDemo;

import WebUITest.base.InitWeb;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class startWeb extends InitWeb {

    WebDriver driver;

    @BeforeClass
    public void setDriver() {

        driver = getDriver();

    }

    @Test
    public void test001(){
        if(driver != null){
            System.out.println("启动成功！！");
        }else{
            System.out.println("启动失败！！");
        }
    }

}
