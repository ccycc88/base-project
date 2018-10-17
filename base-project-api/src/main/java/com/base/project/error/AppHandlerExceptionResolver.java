package com.base.project.error;

import com.api.project.util.StringUtil;
import com.base.project.commcon.vo.ErrorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class AppHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {

    private ErrorProperties errorProperties;

    private static final String ERROR_NAME = "APP.ERROR";

    public AppHandlerExceptionResolver(ServerProperties serverProperties){

        this.errorProperties = serverProperties.getError();
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        httpServletRequest.setAttribute(ERROR_NAME, e);
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public ErrorProperties getErrorProperties() {
        return errorProperties;
    }

    public void setErrorProperties(ErrorProperties errorProperties) {
        this.errorProperties = errorProperties;
    }
    public ErrorVo getError(ServletWebRequest request){

        Throwable t = this.getError(request.getRequest());
        HttpStatus status = this.getHttpStatus(request.getRequest());
        ErrorVo vo = new ErrorVo();
        vo.setError(t.toString());
        vo.setReasonPhrase(status.getReasonPhrase());
        vo.setStackTrace(StringUtil.createStackTrace(t));
        vo.setStatusCode(status.value());
        vo.setTime(LocalTime.now().toString());
        vo.setUrl((String) request.getRequest().getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));
        return vo;
    }

    /**
     * 获取requset异常
     * @param request
     * @return
     */
    private Throwable getError(HttpServletRequest request){

        Throwable th = (Throwable) request.getAttribute(ERROR_NAME);
        if(th == null){

            th = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        }
        if(th != null){

            while(th instanceof ServletException && th.getCause() != null){

                th = th.getCause();
            }
        }else{

            String message = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
            if(StringUtil.isBlank(message)){

                HttpStatus status = this.getHttpStatus(request);
                message = "Unknown Exception With " + status.value() + " " + status.getReasonPhrase();
            }
            th = new Exception(message);
        }
        return th;
    }
    private HttpStatus getHttpStatus(HttpServletRequest request){

        Integer status = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        try {

            return status == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(status);
        }catch (Exception e){

            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    private boolean isIncludeStackTrace(HttpServletRequest request){

        ErrorProperties.IncludeStacktrace includeStacktrace = this.errorProperties.getIncludeStacktrace();
        if(includeStacktrace == ErrorProperties.IncludeStacktrace.ALWAYS){

            return true;
        }
        if(includeStacktrace == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM){

            String trace = request.getParameter("trace");
            if(StringUtil.isBlank(trace) &&  !"false".equals(trace.toLowerCase())){

                return true;
            }
        }
        return false;
    }
}
