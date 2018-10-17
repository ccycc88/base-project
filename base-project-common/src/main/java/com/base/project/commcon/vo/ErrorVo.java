package com.base.project.commcon.vo;

public class ErrorVo implements IVO {

    private static final long serialVersionUID = 3611725413798210653L;
    /**
     * 错误生成时间
     */
    private String time = null;
    /**
     * 访问url
     */
    private String url = null;
    /**
     * 错位类型
     */
    private String error = null;
    /**
     * 错误堆栈
     */
    private String stackTrace = null;
    /**
     * 状态码
     */
    private int statusCode = -1;
    /**
     * 状态码描述
     */
    private String reasonPhrase = null;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}
