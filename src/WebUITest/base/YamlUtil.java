package WebUITest.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YamlUtil {
//    private String yamlfile;
//    private WebDriver driver;
//    private Map<String,Map<String,String>> ml;
//    public YamlUtil(){
//        this.yamlfile =System.getProperty("user.dir") + "\\src\\WebUITest\\pages\\test.yaml";
////        this.driver=driver;
//        getYamlFile();
//    }
//    public void getYamlFile(){
//        File f = new File(this.yamlfile);
//
//        try {
//            ml= Yaml.loadType(new FileInputStream(f.getAbsolutePath()), HashMap.class);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//    //创建by对象
//    private By getBy(String key){
//        By by = null;
//        if (ml.containsKey(key)){
//            Map<String,String> m = ml.get(key);
//            String type = m.get("type");
//            String value = m.get("value");
//            switch(type){
//                case "id":
//                    by = By.id(value);
//                    break;
//                case "name":
//                    by = By.name(value);
//                    break;
//                case "xpath":
//                    by = By.xpath(value);
//                    break;
//                case "class":
//                    by = By.className(value);
//                    break;
//                case "linkText":
//                    by = By.linkText(value);
//                    break;
//                case "cssSelector":
//                    by= By.cssSelector(value);
//                    break;
//            }
//        }
//        else
//        {
//            System.out.println("Locator "+key+" is not exist in  "+yamlfile);
//        }
//        return by;
//    }
//    //根据名称 返回webElement对象
//    public WebElement getElement(String key){
//        By by = this.getBy(key);
//        WebElement  element = driver.findElement(by);
//        return element;
//    }
}
