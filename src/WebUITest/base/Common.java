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


    public void testLoadYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File ymlFile = new File(System.getProperty("user.dir") + "\\src\\WebUITest\\pages\\test.yaml");
        Map<String, Map<String, String>> test1 = Yaml.loadType(new FileInputStream(ymlFile), HashMap.class);
        if (test1.containsKey("logout")) {
            Map<String, String> m = test1.get("logout");
            String type = m.get("type");
            String value = m.get("value");
            System.out.println(type);
            System.out.println(value);
        }

    }

}
