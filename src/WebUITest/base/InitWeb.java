package WebUITest.base;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class InitWeb {

    private WebDriver driver;

    @BeforeSuite
    public void startTest() {

        try {
            Properties props = new Properties();//创建一个系统参数对象
            FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\log4j.properties");
            props.load(fis1);//将配置加载到系统参数对象中
            fis1.close();
            PropertyConfigurator.configure(props);//装入log4j配置信息
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Common.LoadYaml("hrweixin.yaml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        String url = "https://www.baidu.com";
        String url = "https://pre-hrweixin.hrcfc.com/hrwx/wechat/sign/login";
        String objPath = System.getProperty("user.dir") + "\\src\\WebUITest\\Driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", objPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(500, 700));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    protected WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

}
