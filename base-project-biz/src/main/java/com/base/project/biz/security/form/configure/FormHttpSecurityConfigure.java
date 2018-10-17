package com.base.project.biz.security.form.configure;

import com.base.project.commcon.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * form表单安全过滤
 */
@Component
public class FormHttpSecurityConfigure {

    @Autowired
    private AuthenticationSuccessHandler formAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler formAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(Constant.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(Constant.DEFAULT_FORM_LOGIN_PROCESSING_URL)
                .successHandler(formAuthenticationSuccessHandler)
                .failureHandler(formAuthenticationFailureHandler);
    }
}
