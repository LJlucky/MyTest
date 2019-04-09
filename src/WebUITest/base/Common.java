package WebUITest.base;

//import org.yaml.snakeyaml.Yaml;

import org.ho.yaml.Yaml;
import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Common {
    public static Map<String, Map<String, String>> test1;
    static File ymlFile;
//读取YAML文件
    public static void LoadYaml(String yamlName) throws FileNotFoundException {
        Yaml yaml = new Yaml();
//        yamlName = "test.yaml";
        String path = System.getProperty("user.dir") + "\\src\\WebUITest\\pages\\" + yamlName;
        ymlFile = new File(path);
    }

    //读取YAML文件
    public static Map<String, Map<String, String>> getYaml(String key) throws FileNotFoundException {
//        LoadYaml("test.yaml");
        test1 = Yaml.loadType(new FileInputStream(ymlFile), HashMap.class);
        Map<String, String> m = null;
        if (test1.containsKey(key)) {
            m = test1.get(key);
            String type = m.get("type");
            String value = m.get("value");
            System.out.println(type);
            System.out.println(value);
        }
        return test1;

    }

}
