package InterfaceTest.base;

import AndroidTest.base.Common;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public Properties prop;
    public int RESPNSE_STATUS_CODE_200 = 200;
    public int RESPNSE_STATUS_CODE_201 = 201;
    public int RESPNSE_STATUS_CODE_404 = 404;
    public int RESPNSE_STATUS_CODE_500 = 500;

    public TestBase() {

        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\InterfaceTest\\config\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Properties props = new Properties();//创建一个系统参数对象
            FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\log4j.properties");
            props.load(fis1);//将配置加载到系统参数对象中
            fis1.close();
            PropertyConfigurator.configure(props);//装入log4j配置信息
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    @AfterSuite
    public void stopDriver() {
        Common.copyReport();

    }

}
