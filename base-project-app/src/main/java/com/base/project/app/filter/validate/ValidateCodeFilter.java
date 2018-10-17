package com.base.project.app.filter.validate;

import com.api.project.util.StringUtil;
import com.base.project.biz.security.validate.exception.ValidateCodeException;
import com.base.project.biz.security.validate.processor.ValidateCodeProcessorHolder;
import com.base.project.biz.security.validate.processor.intfs.ValidateCodeProcessor;
import com.base.project.commcon.constant.Constant;
import com.base.project.commcon.vo.code.ValidateCode;
import com.base.project.commcon.vo.code.ValidateCodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 验证码过滤器
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Map<String, ValidateCodeType> urls = new HashMap<>();

    private AntPathMatcher matcher = new AntPathMatcher();

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    private Logger logger = LoggerFactory.getLogger(ValidateCodeFilter.class);
    /**
     * 初始化要拦截的url信息
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urls.put(Constant.DEFAULT_FORM_LOGIN_PROCESSING_URL, ValidateCodeType.IMAGE);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = this.getValidateCodeType(httpServletRequest);
        if(type == null){

            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        ValidateCodeProcessor processor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
        try {
            processor.validate(new ServletWebRequest(httpServletRequest, httpServletResponse));

            logger.debug("验证码验证成功");
        } catch (ValidateCodeException e) {

            logger.error("验证码验证异常[" + StringUtil.createStackTrace(e) + "]");
            authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }
    }
    private ValidateCodeType getValidateCodeType(HttpServletRequest request){

        if("post".equalsIgnoreCase(request.getMethod())){

            Iterator<Map.Entry<String, ValidateCodeType>> it = urls.entrySet().iterator();

            while(it.hasNext()){

                Map.Entry<String, ValidateCodeType> en = it.next();
                if(matcher.match(en.getKey(), request.getRequestURI())){

                    return en.getValue();
                }
            }
        }
        return null;
    }
}
