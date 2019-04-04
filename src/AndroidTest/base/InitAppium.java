package AndroidTest.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class InitAppium {

    private static AndroidDriver driver;

    @BeforeSuite
    public void appiumStart() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("deviceName", "Nexus4");
        capabilities.setCapability("udid", "192.168.22.101:5555");
        capabilities.setCapability("appPackage", "com.hrcfc.hrApp.custManager");
        capabilities.setCapability("appActivity", ".base.LaunchActivity_");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("unicodeKeyboard", "true");
//        capabilities.setCapability("automationName","UIAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterSuite
    public void stopDriver() {
        Common.copyReport();
        driver.quit();

    }

    public AndroidDriver getDriver() {
        if (driver != null) {
            System.out.println("创建driver成功！");
        }
        return driver;
    }


}
