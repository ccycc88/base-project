package com.base.project.biz.security.validate.generate.achieve;

import com.api.project.util.RandomUtil;
import com.base.project.biz.security.validate.generate.ValidateCodeGenerator;
import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.sms.SmsValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(ServletWebRequest request) {

        //生成短信验证码
        String codeContext = RandomUtil.getRandomDigitsNoRepeat(4);
        SmsValidateCode code = new SmsValidateCode(codeContext, 60);
        return code;
    }
}
