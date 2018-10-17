package com.base.project.commcon.vo.code.sms;

import com.base.project.commcon.vo.code.ValidateCode;

import java.time.LocalDateTime;

public class SmsValidateCode extends ValidateCode {
    public SmsValidateCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public SmsValidateCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }
}
