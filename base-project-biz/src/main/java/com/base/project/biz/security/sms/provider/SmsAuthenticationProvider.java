package com.base.project.biz.security.sms.provider;

import com.base.project.biz.security.sms.token.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService service = null;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
        UserDetails userDetails = service.loadUserByUsername((String)authentication.getPrincipal());
        if(userDetails == null){

            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        SmsAuthenticationToken token = new SmsAuthenticationToken(authentication.getPrincipal(), userDetails.getAuthorities());
        token.setDetails(smsAuthenticationToken.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getService() {
        return service;
    }

    public void setService(UserDetailsService service) {
        this.service = service;
    }
}
