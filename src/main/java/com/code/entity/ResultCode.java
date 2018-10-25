package com.code.entity;

/**
 * 定义枚举类维护状态码
 *
 * @author Admin
 */
public enum ResultCode {
    /**
     * 返回成功
     */
    SUCCESS("验证码正确", "true"),
    /**
     * 返回失败
     */
    ERROR("验证码错误", "false");

    private String msg;
    private String result;

    ResultCode(String msg, String result) {
        this.msg = msg;
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public String getResult() {
        return result;
    }
}
