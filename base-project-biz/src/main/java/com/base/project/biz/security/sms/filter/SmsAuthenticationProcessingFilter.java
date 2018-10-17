package com.base.project.biz.security.sms.filter;

import com.base.project.biz.security.sms.token.SmsAuthenticationToken;
import com.base.project.commcon.constant.Constant;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SmsAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private volatile boolean postOnly = true;

    public SmsAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher(Constant.DEFAULT_MOBILE_LOGIN_PROCESSING_URL, "POST"));
    }
    protected SmsAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if(postOnly && !httpServletRequest.getMethod().equals("POST")){

            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }
        String mobile = this.obtainMobile(httpServletRequest);
        mobile = mobile.trim();
        SmsAuthenticationToken token = new SmsAuthenticationToken(mobile);
        this.setDetails(httpServletRequest, token);

        //manager 在这里调用provider
        return this.getAuthenticationManager().authenticate(token);
    }
    private void setDetails(HttpServletRequest request, SmsAuthenticationToken token){

        token.setDetails(authenticationDetailsSource.buildDetails(request));
    }
    /**
     * 获取手机号
     */
    private String obtainMobile(HttpServletRequest request) throws ServletRequestBindingException {
        return ServletRequestUtils.getStringParameter(request, "mobile", "");
    }
    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
