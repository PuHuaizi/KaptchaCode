package com.code.controller;

import com.code.http.HttpAPIService;
import com.code.http.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 */
@RestController
public class HttpClientController {

    //@Resource
    //private HttpAPIService httpAPIService;

    @RequestMapping("/cookies")
    public String getCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // HttpResult result = new HttpResult();
        // result.setBody(httpAPIService.doGet("https://ids.cdstm.cn:8443/zgkjg/doLogin.jsp"));
        String url = "http://192.168.2.7:8080/zgkjg/ids/sendSMS.do";

        response.setContentType("application/javascript; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        Map<String, Object> params = new HashMap<>();
        params.put("jsoncallback", request.getAttribute("jsoncallback"));
        params.put("mobile", request.getAttribute("mobile"));
        params.put("_", request.getAttribute("_"));

        Map<String, String> headers = new HashMap<>();
        Enumeration requestHeaders = request.getHeaderNames();
        while (requestHeaders.hasMoreElements()) {
            String headerName = (String) requestHeaders.nextElement();
            String headValue = request.getHeader(headerName);
            System.out.println(headerName + "=" + headValue);

            headers.put(headerName, headValue);
        }

        String result = HttpAPIService.get(url, params, headers);

        // 创建 HttpClient 实例
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 HttpGet 实例
        //HttpGet httpGet = new HttpGet("https://www.baidu.com");
        // 执行http get请求
        // CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        // 获取返回实体
        // HttpEntity entity = closeableHttpResponse.getEntity();
        // 获取网页内容
        // System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8"));
        // response关闭
        // closeableHttpResponse.close();
        // httpClient关闭
        // httpClient.close();
        
        return result;
    }
}
