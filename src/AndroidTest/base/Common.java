package AndroidTest.base;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Common {

    //重命名文件
    public static void renameFile(String path, String oldName, String newName) {
        if (!oldName.equals(newName)) {
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (newFile.exists()) {
                System.out.println(newName + "已经存在");
            } else {
                oldFile.renameTo(newFile);
            }

        }
    }

    //拷贝文件
    public static boolean copyFile(String file1, String file2) {

        File in = new File(file1);
        File out = new File(file2);
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "源文件路径错误！！！");
            return false;
        }
        /*else{
            System.out.println("源文件路径"+in.getAbsolutePath());
            System.out.println("目标路径"+out.getAbsolutePath());
        }*/
        if (!out.exists()) {
            out.mkdirs();
        }
        //File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(in);
            fos = new FileOutputStream(new File(file2 + "\\" + in.getName()));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int c;
        byte[] b = new byte[1024 * 5];

        try {
            while ((c = fis.read(b)) != -1) {
                fos.write(b, 0, c);
            }

            fis.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }


    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 复制测试报告并重命名
     */
    public static void copyReport() {
        String objPath = System.getProperty("user.dir");
//        String oldPath = "D:\\idea-workspace\\TestDemo\\report.html";
//        String newPath1 = "D:\\idea-workspace\\TestDemo\\Report";
////        String newPath2 = "D:\\lijun\\idea-workspace\\hrcfc\\Report\\report";
//        String newPath2 = "D:\\idea-workspace\\TestDemo\\Report";

        String oldPath = objPath + "\\report.html";
        String newPath1 = objPath +  "\\Report";
//        String newPath2 = objPath +  "D:\\lijun\\idea-workspace\\hrcfc\\Report\\report";
        String newPath2 = objPath +  "\\Report";

        String oldName = "report.html";
        String newName = "Android自动化_" + getStringToday() + "_Report.html";
        copyFile(oldPath, newPath1);
        renameFile(newPath2, oldName, newName);
    }

    /**
     //     * 截图
     //     *
     //     *
     //     */
//    public void  Screenshot(String filename){
//        file
//    }
//
//

    /**
     * 读取properties文件
     */
    public static String getProperties(String keyWord) {
        String filePath = "D:\\lijun\\idea-workspace\\hrcfc\\config\\user.properties";
        Properties prop = null;
        String value = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("user.properties");
            value = prop.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    //方法一
    public static void readProperty1() {
        Properties properties = new Properties();
        InputStream inputStream = Object.class.getResourceAsStream("/user.properties");
        try {
            properties.load(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.get("warshipType.1"));
    }


}
