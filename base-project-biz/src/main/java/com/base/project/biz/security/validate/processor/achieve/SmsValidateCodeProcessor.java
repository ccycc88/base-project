package com.base.project.biz.security.validate.processor.achieve;

import com.base.project.biz.security.validate.processor.AbstractValidateCodeProcessor;
import com.base.project.commcon.vo.code.sms.SmsValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<SmsValidateCode> {
    @Override
    protected void send(ServletWebRequest request, SmsValidateCode smsValidateCode) throws Exception {
        //实际的发送验证码逻辑
        //业务需要考量 例如
        //1. 同一手机多长时间内可以发送的数量
        //2. 还在有效期内的验证码 在重发后是否可用
        //3. 验证码的有效期 等
        System.out.println(smsValidateCode.getCode());
    }
}
