package WebUITest.testcase;

import WebUITest.base.InitWeb;
import WebUITest.business.HrBusiness;
import WebUITest.testDemo.login;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MianHeTest extends InitWeb {

    private final static Logger Log = Logger.getLogger(MianHeTest.class);
    private WebDriver driver;
    HrBusiness hrBusiness;

    @BeforeClass
    public void setDriver(){
        driver = getDriver();
        hrBusiness = new HrBusiness(driver);
    }

    @Test
    public void test001(){
        hrBusiness.login();
    }

}
