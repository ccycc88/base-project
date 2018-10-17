package com.base.project.biz.security.form.handler;

import com.base.project.commcon.vo.rsp.BaseRespone;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *form鉴权成功处理
 */
@Component("formAuthenticationSuccessHandler")
public class FormAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(FormAuthenticationSuccessHandler.class);
    private Gson gson = new Gson();
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        logger.debug("form登录成功");

        //登录响应类型区分  json 或跳转页面
        if("json" == "json"){

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(gson.toJson(BaseRespone.success()));
            return;
        }else{
            //页面跳转
            requestCache.removeRequest(request, response);
            this.setAlwaysUseDefaultTargetUrl(true);
            this.setDefaultTargetUrl("");

            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
