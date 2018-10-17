package com.base.project.biz.security.validate.processor.achieve;

import com.base.project.biz.security.validate.processor.AbstractValidateCodeProcessor;
import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.image.ImageValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {

    @Override
    protected void send(ServletWebRequest request, ImageValidateCode validateCode) throws Exception{

        ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
