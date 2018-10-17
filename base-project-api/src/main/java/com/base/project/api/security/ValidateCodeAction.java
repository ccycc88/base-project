package com.base.project.api.security;

import com.base.project.biz.security.validate.processor.ValidateCodeProcessorHolder;
import com.base.project.biz.security.validate.processor.intfs.ValidateCodeProcessor;
import com.base.project.commcon.vo.rsp.BaseRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeAction {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(value = "/code/{type:\\w+}")
    public BaseRespone createCode(@PathVariable(value = "type", required = true) String type, HttpServletRequest request, HttpServletResponse response){

        ValidateCodeProcessor processor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
        if(processor == null){

            return BaseRespone.fail("请求参数异常");
        }
        processor.create(new ServletWebRequest(request, response));
        return BaseRespone.success();
    }
}
