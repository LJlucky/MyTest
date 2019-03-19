package InterfaceTest.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

public class RestClient {


    final static Logger Log = Logger.getLogger(RestClient.class);

    //1. Get 请求方法
    public void get1(String url) throws ClientProtocolException, IOException {

        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpget = new HttpGet(url);

        Log.info("开始发送get请求...");
        //执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
        CloseableHttpResponse httpResponse = httpclient.execute(httpget);

        //拿到Http响应状态码，例如和200,404,500去比较
        int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("response status code -->"+responseStatusCode);

        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");

        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println("respon json from API-->" + responseJson);

        //获取响应头信息,返回是一个数组
        Header[] headerArray = httpResponse.getAllHeaders();
        //创建一个hashmap对象，通过postman可以看到请求响应头信息都是Key和value得形式，所以我们想起了HashMap
        HashMap<String, String> hm = new HashMap<String, String>();
        //增强for循环遍历headerArray数组，依次把元素添加到hashmap集合
        for(Header header : headerArray) {
            hm.put(header.getName(), header.getValue());
        }

        //打印hashmap
        System.out.println("response headers -->"+ hm);

    }

    //1.Get请求方法
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpGet = new HttpGet(url);
        Log.info("开始发送get请求...");
        //执行请求，相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        Log.info("发送请求成功！开始得到响应对象。");
        return  httpResponse;
    }

    //2.Get 请求方法（带请求头信息）
    public CloseableHttpResponse get(String url,HashMap<String,String>headermap) throws ClientProtocolException,IOException{
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpget = new HttpGet(url);
        //加载请求头到httpget对象
        for(Map.Entry<String,String> entry:headermap.entrySet()){
            httpget.addHeader(entry.getKey(),entry.getValue());

        }
        //执行请求，相当于postman上点击发送按钮，然后赋值给httpresponse对象接收
        CloseableHttpResponse httpResponse = httpclient.execute(httpget);

        return httpResponse;
    }

    //3.POST方法
    public CloseableHttpResponse post(String url,String entityString,HashMap<String,String>headermap)throws ClientProtocolException,IOException{
//创建一个可关闭的httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个httppost的请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置payload
        httpPost.setEntity(new StringEntity(entityString));

        //加载请求头到httppost对象
        for(Map.Entry<String,String> entry:headermap.entrySet()){
            httpPost.setHeader(entry.getKey(),entry.getValue());

        }

        //发送post请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        Log.info("开始发送post请求");

        return httpResponse;
    }

    /**
     * 获取响应状态码，常用来和TestBase中定义的状态码常量去测试断言使用
     * @param response
     * @return 返回int类型状态码
     */
    public int getStatusCode (CloseableHttpResponse response) {

        int statusCode = response.getStatusLine().getStatusCode();
        Log.info("解析，得到响应状态码:"+ statusCode);
        return statusCode;

    }

    /**
     *
     * @param response, 任何请求返回返回的响应对象
     * @return， 返回响应体的json格式对象，方便接下来对JSON对象内容解析
     * 接下来，一般会继续调用TestUtil类下的json解析方法得到某一个json对象的值
     * @throws ParseException
     * @throws IOException
     */
    public JSONObject getResponseJson (CloseableHttpResponse response) throws ParseException, IOException {
        Log.info("得到响应对象的String格式");
        String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
        JSONObject responseJson = JSON.parseObject(responseString);
        Log.info("返回响应内容的JSON格式");
        return responseJson;
    }



}

