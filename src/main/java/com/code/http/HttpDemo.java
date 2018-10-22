package com.code.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpDemo {
    public static void doGet() throws IOException {
        // 创建 HttpClient 实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 HttpGet 实例
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        // 执行http get请求
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        // 获取返回实体
        HttpEntity entity = closeableHttpResponse.getEntity();
        // 获取网页内容
        System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8"));
        // response关闭
        closeableHttpResponse.close();
        // httpClient关闭
        httpClient.close();
    }
}
