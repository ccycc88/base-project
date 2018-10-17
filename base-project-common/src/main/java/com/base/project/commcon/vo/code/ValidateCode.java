package com.base.project.commcon.vo.code;

import com.base.project.commcon.vo.IVO;

import java.time.LocalDateTime;

public class ValidateCode implements IVO {

    /**
     * 验证码内容
     */
    private String code = null;
    private LocalDateTime expireTime = null;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
    public void setExpireTime(long seconds){

        LocalDateTime.now().plusSeconds(seconds);
    }
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
