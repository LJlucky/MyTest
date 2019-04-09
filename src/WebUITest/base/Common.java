package WebUITest.base;

//import org.yaml.snakeyaml.Yaml;

import org.apache.log4j.Logger;
import org.ho.yaml.Yaml;
import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Common {
    private final static Logger Log = Logger.getLogger(Common.class);
    public static Map<String, Map<String, String>> test1;
    static File ymlFile;
//读取YAML文件
    public static void LoadYaml(String yamlName) throws FileNotFoundException {
        Log.info("==========开始加载"+ yamlName +"元素==========");
        Yaml yaml = new Yaml();
//        yamlName = "test.yaml";
        String path = System.getProperty("user.dir") + "\\src\\WebUITest\\pages\\" + yamlName;
        ymlFile = new File(path);
    }

    //读取YAML文件
    public static Map<String, Map<String, String>> getYaml() throws FileNotFoundException {
        test1 = Yaml.loadType(new FileInputStream(ymlFile), HashMap.class);
        if(test1 != null){
            Log.info("==========元素加载成功==========");
        }
//        Map<String, String> m = null;
//        if (test1.containsKey(key)) {
//            m = test1.get(key);
//            String type = m.get("type");
//            String value = m.get("value");
//            System.out.println(type);
//            System.out.println(value);
//        }
        return test1;

    }

}
