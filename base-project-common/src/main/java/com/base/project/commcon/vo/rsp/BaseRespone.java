package com.base.project.commcon.vo.rsp;

public class BaseRespone {
    private int code;
    private String msg;
    private Object data;

    private static final int SUCCESS = 0;
    private static final int FAIL = 1;
    private static final int ILLEGAL = 2;

    public BaseRespone() {

        this("", SUCCESS);
    }
    public BaseRespone(String msg, int code) {

        this(msg, code, "");
    }
    public BaseRespone(String msg, int code, Object data) {

        this.msg = msg;
        this.code = code;
        this.data = data;
    }
    public static BaseRespone fail(String msg) {

        return new BaseRespone(msg, FAIL);
    }
    public static BaseRespone illegal() {

        return new BaseRespone("非法用户，请登陆", ILLEGAL);
    }
    public static BaseRespone success() {

        return new BaseRespone("", SUCCESS);
    }
    public static BaseRespone success(Object data) {

        return new BaseRespone("", SUCCESS, data);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
