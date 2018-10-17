package com.base.project.biz.security.validate.generate.achieve;

import com.api.project.util.VerifyCodeUtil;
import com.base.project.biz.security.validate.generate.ValidateCodeGenerator;
import com.base.project.commcon.vo.code.image.ImageValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * 图形验证码生成器
 */
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageValidateCode generate(ServletWebRequest request) {

        String code = VerifyCodeUtil.generateVerifyCode(4);
        BufferedImage image = VerifyCodeUtil.outputVerifyImage(150, 50, code);
        ImageValidateCode imageValidateCode = new ImageValidateCode(code, 60, image);
        return imageValidateCode;
    }
}
