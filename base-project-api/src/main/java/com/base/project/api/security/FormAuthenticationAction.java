package com.base.project.api.security;

import com.base.project.commcon.constant.Constant;
import com.base.project.commcon.vo.rsp.BaseRespone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FormAuthenticationAction {

    private Logger logger = LoggerFactory.getLogger(FormAuthenticationAction.class);
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 当需要身份认证时跳转这里
     * 非前后端分离跳转登录页面
     * 前后端分离返回未鉴权状态码
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = Constant.DEFAULT_UNAUTHENTICATION_URL, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void requireAuthenticationHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null){

            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("请求地址地址[" + redirectUrl + "]");
            redirectStrategy.sendRedirect(request, response, "/webapp/signIn.html");
        }
    }
    @GetMapping(value = Constant.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseRespone requireAuthenticationJson(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return BaseRespone.illegal();
    }
}
