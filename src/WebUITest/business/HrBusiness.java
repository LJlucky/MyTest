package WebUITest.business;

import WebUITest.opeartion.HrOpeartion;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class HrBusiness {
    private final static Logger Log = Logger.getLogger(HrBusiness.class);
    WebDriver driver;
    HrOpeartion hrOpeartion;

    public HrBusiness(WebDriver driver) {
        this.driver = driver;
        hrOpeartion = new HrOpeartion(driver);
    }

    public void login() {
        hrOpeartion.inputLoginName();
        hrOpeartion.inputLoginPwd();
        hrOpeartion.clickLogin();
        hrOpeartion.clickMain();
    }

}
