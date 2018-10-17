package com.base.project.biz.security.form.handler;

import com.base.project.commcon.vo.rsp.BaseRespone;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("formAuthenticationFailureHandler")
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(FormAuthenticationFailureHandler.class);
    private Gson gson = new Gson();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.debug("form登录成功");

        //AuthenticationException  可以依据不同的异常进行不同的处理
        if("json" == "json"){

            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(gson.toJson(BaseRespone.fail(exception.getMessage())));
        }else{

            //直接跳转页面
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
