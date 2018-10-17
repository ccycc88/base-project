package com.base.project.biz.security.validate.repository.achieve;

import com.base.project.biz.security.validate.repository.ValidateCodeRepository;
import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *sesion 验证码仓库
 *
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    private final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public SessionValidateCodeRepository() {
        super();
    }

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {

        sessionStrategy.setAttribute(request,
                this.getSessionKey(request, type), code);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {

        sessionStrategy.removeAttribute(request, this.getSessionKey(request, type));
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(request, this.getSessionKey(request, type));
    }
    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }
}
