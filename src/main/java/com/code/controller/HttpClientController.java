package com.code.controller;

import com.code.http.HttpAPIService;
import com.code.http.HttpResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Admin
 */
@RestController
public class HttpClientController {

    @Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("/cookies")
    public HttpResult getCookies() throws Exception {
        HttpResult result = new HttpResult();
        result.setBody(httpAPIService.doGet("http://www.baidu.com"));
        result.setCode(1);
        System.out.println(result);
        return result;
    }
}
