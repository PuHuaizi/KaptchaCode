package com.code.controller;

import ch.qos.logback.core.pattern.FormatInfo;
import com.code.http.HttpAPIService;
import com.code.http.HttpResult;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

    static Gson gson = new Gson();

    //@Resource
    //private HttpAPIService httpAPIService;

    @RequestMapping("/cookies")
    public String getCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // String url = "http://192.168.2.7:8080/zgkjg/ids/sendSMS.do";
        String url = "https://www.baidu.com/";

        response.setContentType("application/javascript; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        System.out.println("要发送的jsoncallback：" + request.getAttribute("jsoncallback"));
        System.out.println("要发送的mobile：" + request.getAttribute("mobile"));
        System.out.println("要发送的_：" + request.getAttribute("_"));

        Map<String, Object> params = new HashMap<>();
        params.put("jsoncallback", request.getAttribute("jsoncallback"));
        params.put("mobile", request.getAttribute("mobile"));
        params.put("_", request.getAttribute("_"));

        Map<String, String> headers = new HashMap<>();
        Enumeration requestHeaders = request.getHeaderNames();
        while (requestHeaders.hasMoreElements()) {
            String headerName = (String) requestHeaders.nextElement();
            String headValue = request.getHeader(headerName);
            System.out.println(headerName + " : " + headValue);

            headers.put(headerName, headValue);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");

        // String result = HttpAPIService.get(url, params, headers);
        HttpResponse httpResponse = HttpAPIService.get(url, null, null);

        HashMap<String, String> map = new HashMap<>(16);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            Header[] allHeaders = httpResponse.getAllHeaders();
            for (Header header : allHeaders) {
                response.addHeader(header.getName(), header.getValue());
                System.out.println(header.getName() + " : " + header.getValue());
            }
            map.put("msg", "验证码发送成功");
            map.put("result", "true");
            return gson.toJson(map);
        } else {
            map.put("msg", "验证码发送失败");
            map.put("result", "false");
            return gson.toJson(map);
        }

    }
}
