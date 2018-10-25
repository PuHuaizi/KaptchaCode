package com.code.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.entity.ResultCode;
import com.code.entity.ResultEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * @author Admin
 */
// @Controller
@RestController
public class KaptchaController {

    static Gson gson = new Gson();

    /**
     * 1、验证码工具
     */
    @Autowired
    DefaultKaptcha kaptcha;

    /**
     * 2、生成验证码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/Kaptcha.jpg")
    public void getKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = kaptcha.createText();
            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 设置 Session 过期时间（单位：秒）
            httpServletRequest.getSession().setMaxInactiveInterval(120);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = kaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 3、校对验证码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    // @RequestMapping("/verify")
    // public ModelAndView imgVerifyCode(HttpServletRequest httpServletRequest,
    //                                   HttpServletResponse httpServletResponse) {
    //     ModelAndView andView = new ModelAndView();
    //     String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
    //     String tryCode = httpServletRequest.getParameter("tryCode");
    //     System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);
    //     if (!rightCode.equals(tryCode)) {
    //         andView.addObject("info", "错误的验证码");
    //         andView.setViewName("index");
    //     } else {
    //         andView.addObject("info", "登录成功");
    //         andView.setViewName("success");
    //     }
    //     // return andView;
    //     return new ModelAndView("redirect:/cookies");
    // }

    /**
     * 3、校对验证码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/verify")
    public ResultEntity imgVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 得到请求的参数Map，注意map的value是String数组类型
        // Map map = request.getParameterMap();
        // Set<String> keySet = map.keySet();
        // for (String key : keySet) {
        //     String[] values = (String[]) map.get(key);
        //     for (String value : values) {
        //         System.out.println(key+"="+value);
        //     }
        // }

        String jsoncallback = request.getParameter("jsoncallback");
        String mobile = request.getParameter("mobile");
        String _ = request.getParameter("_");

        System.out.println("前台获取到的jsoncallback：" + jsoncallback);
        System.out.println("前台获取到的mobile：" + mobile);
        System.out.println("前台获取到的_：" + _);

        // String jsoncallback = "jQuery21405098761300080115_1539846799825";
        // String mobile = "15261660178";
        // String _ = "1539846799827";

        request.setAttribute("jsoncallback", jsoncallback);
        request.setAttribute("mobile", mobile);
        request.setAttribute("_", _);

        // Enumeration requestHeaders = request.getHeaderNames();
        // while (requestHeaders.hasMoreElements()) {
        //     String headerName = (String) requestHeaders.nextElement();
        //     String headValue = request.getHeader(headerName);
        //     System.out.println(headerName + "=" + headValue);
        // }


        String rightCode = (String) request.getSession().getAttribute("rightCode");
        String tryCode = request.getParameter("tryCode");
        System.out.println("rightCode：" + rightCode + " —————————— tryCode：" + tryCode);
        // HashMap<String, String> map = new HashMap<>(16);

        if (!rightCode.equals(tryCode)) {
            // response.sendRedirect("/index");

            // map.put("msg", "验证码错误");
            // map.put("result", "false");

            ResultEntity resultEntity = new ResultEntity(ResultCode.ERROR);
            return resultEntity;
        } else {
            // 测试session的最大存活时间
            // response.sendRedirect("/session");
            // request.getRequestDispatcher("/cookies").forward(request, response);
            // return null;

            // map.put("msg", "验证码正确");
            // map.put("result", "true");
            // JsonElement element = gson.toJsonTree(map);
            // return gson.toJson(map);

            ResultEntity resultEntity = new ResultEntity(ResultCode.SUCCESS);
            return resultEntity;
        }
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * Java后台获取session的所有内容（获取到key和value的方法）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String getSessionId(HttpServletRequest request) {
        // 获取session
        HttpSession session = request.getSession();
        // 获取session中所有的键值
        Enumeration<String> attrs = session.getAttributeNames();
        // 遍历attrs中的
        while (attrs.hasMoreElements()) {
            // 获取session键值
            String name = attrs.nextElement();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("----------" + name + ":" + value + "----------\n");
            System.out.println(">>>>>>>>>>" + session.getId() + ":" + session.getMaxInactiveInterval() + "<<<<<<<<<<\n");
        }
        return null;
    }

}
