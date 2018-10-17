package com.base.project.biz.security.sms.configure;

import com.base.project.biz.security.sms.filter.SmsAuthenticationProcessingFilter;
import com.base.project.biz.security.sms.provider.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsHttpSecurityConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("smsUserDetailsService")
    private UserDetailsService userDetailsService;
    @Override
    public void configure(HttpSecurity builder) throws Exception {

        SmsAuthenticationProcessingFilter filter = new SmsAuthenticationProcessingFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
        provider.setService(userDetailsService);

        builder.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);   // 将过滤器添加到UsernamePasswordAuthenticationFilter后面
    }
}
