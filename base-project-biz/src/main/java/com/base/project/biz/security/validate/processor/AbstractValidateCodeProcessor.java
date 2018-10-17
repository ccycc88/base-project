package com.base.project.biz.security.validate.processor;

import com.base.project.biz.security.validate.exception.ValidateCodeException;
import com.base.project.biz.security.validate.generate.ValidateCodeGenerator;
import com.base.project.biz.security.validate.processor.intfs.ValidateCodeProcessor;
import com.base.project.biz.security.validate.repository.ValidateCodeRepository;
import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    protected final String SESSION_KEY_PREFIX = "SESSION_KEY_CODE_";

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    //不同验证码可能会考虑不同的仓库存储
    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws ValidateCodeException {

        //创建验证码
        T t = this.generate(request);
        //将验证码保存到验证码仓库中
        validateCodeRepository.save(request, t, this.getValidateCodeType(request));
        //验证码发送
        try {
            this.send(request, t);
        } catch (Exception e) {
            throw new ValidateCodeException(e.getMessage());
        }
    }

    @Override
    public void validate(ServletWebRequest request) throws ValidateCodeException {

        ValidateCodeType codeType = getValidateCodeType(request);

        T codeInSession = (T) validateCodeRepository.get(request, codeType);

        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getValidateType());

        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不可为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }

        validateCodeRepository.remove(request, codeType);
    }
    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");

        return ValidateCodeType.valueOf(type.toUpperCase());
    }
    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    private T generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (T) validateCodeGenerator.generate(request);
    }
    protected abstract void send(ServletWebRequest request, T t) throws Exception;
}
