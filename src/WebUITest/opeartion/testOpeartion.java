package WebUITest.opeartion;

import WebUITest.base.WebUI;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class testOpeartion extends WebUI {

    public testOpeartion(WebDriver driver) {
        super(driver);
    }

    public void testBaiduClick() throws FileNotFoundException {
        click(getElement("baidu1"));
    }

    public void testHr() throws FileNotFoundException {
        click(getElement("loginName"));
    }

}
