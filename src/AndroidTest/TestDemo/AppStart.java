package AndroidTest.TestDemo;

import AndroidTest.base.InitAppium;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;

public class AppStart {
    public static void main(String[] args){
        AndroidDriver driver;
        System.out.println("测试一下！！！！！！！！！");
        InitAppium apptest = new InitAppium();
        try {
            apptest.appiumStart();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
