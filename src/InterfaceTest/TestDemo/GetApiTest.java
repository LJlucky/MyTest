package InterfaceTest.TestDemo;


import java.io.IOException;

import InterfaceTest.base.TestBase;
import InterfaceTest.restclient.RestClient;
import InterfaceTest.util.TestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import report.ZTestReport;

@Listeners({ZTestReport.class})
public class GetApiTest extends TestBase {
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host + "/api/users";

    }

    @Test
    public void getAPITest() throws ClientProtocolException, IOException {
        restClient = new RestClient();
        closeableHttpResponse=restClient.get(url);
        //断言状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPNSE_STATUS_CODE_200,"response status code is not 200");

        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        //创建Json对象，把上面的字符串系列化成Json对象
        JSONObject responseJson = JSON.parseObject(responseString);

        //Json内容解析
        String s = TestUtil.getValueByJPath(responseJson,"data[0]/first_name");
        System.out.println(s);//George
        Assert.assertEquals(s,"George","first name is not Eve");

        String s1 = TestUtil.getValueByJPath(responseJson,"data[1]/first_name");
        System.out.println(s1);//George
        Assert.assertEquals(s1,"Janet","first name is not Eve");

        String s2 = TestUtil.getValueByJPath(responseJson,"data[2]/first_name");
        System.out.println(s2);//George
        Assert.assertEquals(s2,"Emma","first name is not Eve");
    }
}

