package InterfaceTest.TestDemo;

import InterfaceTest.base.TestBase;
import InterfaceTest.data.Users;
import InterfaceTest.restclient.RestClient;
import InterfaceTest.util.TestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends TestBase {

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
    public void postApiTest()throws ClientProtocolException,IOException

    {
        restClient = new RestClient();

        //准备请求头信息
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");

        //对象转换成json字符串
        Users user = new Users("Lij", "tester");
        String userJosnString = JSON.toJSONString(user);
        System.out.println(userJosnString);
        try {
            closeableHttpResponse = restClient.post(url, userJosnString, headermap);
        } catch (Exception e) {

        }


        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_201, "status code is not 201");

        //断言响应json内容中的name和job是不是期待的结果
        String responseString = null;
        try {
            responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        } catch (Exception e) {

        }


        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println(responseString);


        String name = TestUtil.getValueByJPath(responseJson, "name");
        String job = TestUtil.getValueByJPath(responseJson, "job");
        Assert.assertEquals(name, "Lij", "name is not same");
        Assert.assertEquals(job, "tester", "job is not same");
    }
}
