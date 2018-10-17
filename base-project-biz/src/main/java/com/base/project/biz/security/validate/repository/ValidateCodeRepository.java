package com.base.project.biz.security.validate.repository;

import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码仓库
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code
     * @param type
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type);

    /**
     * 移除验证码
     * @param request
     * @param type
     */
    void remove(ServletWebRequest request, ValidateCodeType type);

    /**
     * 获取验证码
     * @param request
     * @param type
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType type);
}
