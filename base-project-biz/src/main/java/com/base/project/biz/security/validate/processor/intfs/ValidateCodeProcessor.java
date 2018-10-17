package com.base.project.biz.security.validate.processor.intfs;

import com.base.project.biz.security.validate.exception.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {

    void create(ServletWebRequest request) throws ValidateCodeException;

    void validate(ServletWebRequest request) throws ValidateCodeException;
}
