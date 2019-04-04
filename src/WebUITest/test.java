package WebUITest;

import WebUITest.base.Common;
import WebUITest.base.WebUI;

public class test {
    public static void main(String[] args) throws Exception {

        Common test1 = new Common();
        WebUI test = new WebUI();
        test.getElement("logoutConfirm");

    }
}
