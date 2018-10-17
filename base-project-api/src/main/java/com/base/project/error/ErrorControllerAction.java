package com.base.project.error;

import com.base.project.commcon.vo.ErrorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("${server.error.path:/error}")
public class ErrorControllerAction implements ErrorController {

    private static final String DEFAULT_ERROR_VIEW = "error";

    @Autowired
    private AppHandlerExceptionResolver appHandlerExceptionResolver;

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView view = new ModelAndView(DEFAULT_ERROR_VIEW, "ERROR", appHandlerExceptionResolver.getError(new ServletWebRequest(request, response)));
        return view;
    }
    @RequestMapping
    @ResponseBody
    public ErrorVo errorJson(HttpServletRequest request, HttpServletResponse response){

        return appHandlerExceptionResolver.getError(new ServletWebRequest(request, response));
    }
    @Override
    public String getErrorPath() {
        return appHandlerExceptionResolver.getErrorProperties().getPath();
    }
}
