package AndroidTest.TestDemo;


import AndroidTest.base.InitAppium;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import report.ZTestReport;

@Listeners({ZTestReport.class})
public class TestNgDemo extends InitAppium {


    @BeforeClass
    public void appStart() {
        AndroidDriver driver;
        driver = getDriver();
        System.out.println("测试一下！！！！！！！！！");
    }

    @Test
    public void test001(){
        System.out.println("测试TestNG");
    }

}
