package com.code.entity;

/**
 * @author Admin
 */
public class ResultEntity {
    /**
     * 状态码
     */
    private String msg;
    /**
     * 描述
     */
    private String result;
    /**
     * 数据
     */
    // private Object data;

    public ResultEntity() {
    }

    public ResultEntity(ResultCode resultCode) {
        this.msg = resultCode.getMsg();
        this.result = resultCode.getResult();
    }

    // public ResultEntity(ResultCode resultCode, Object data) {
    //     this(resultCode);
    //     this.data = data;
    // }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // public Object getData() {
    //     return data;
    // }

    // public void setData(Object data) {
    //     this.data = data;
    // }
}
