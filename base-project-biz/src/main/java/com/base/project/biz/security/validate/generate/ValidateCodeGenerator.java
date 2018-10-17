package com.base.project.biz.security.validate.generate;

import com.base.project.commcon.vo.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
