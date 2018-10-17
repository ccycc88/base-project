package com.base.project.biz.security.validate.processor;

import com.api.project.util.StringUtil;
import com.base.project.biz.security.validate.processor.intfs.ValidateCodeProcessor;
import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(String type){

        if(StringUtil.isBlank(type)){

            throw new IllegalArgumentException("验证类型不可为空");
        }
        String processorKey = type.concat(ValidateCodeProcessor.class.getSimpleName());
        return validateCodeProcessors.get(processorKey);
    }
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){

        return this.findValidateCodeProcessor(type.toString().toLowerCase());
    }
}
