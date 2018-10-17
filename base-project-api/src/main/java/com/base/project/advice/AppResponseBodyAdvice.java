package com.base.project.advice;

import com.base.project.commcon.annotation.des.EncryptResponBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;

/**
 * 这里只针对application/json的数据进行响应加密
 */
@ControllerAdvice
public class AppResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        if(GsonHttpMessageConverter.class.isAssignableFrom(aClass)){

            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        boolean encrypt = false;
        if(methodParameter.getMethod().isAnnotationPresent(EncryptResponBody.class)) {

            EncryptResponBody encryptBody = methodParameter.getMethodAnnotation(EncryptResponBody.class);
            encrypt = encryptBody.encrypt();
        }
        if(encrypt) {

            try {

                //对数据进行加密返回
                byte[] buf = this.toEncrypt(o);
                serverHttpResponse.getBody().write(buf);
                return null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return o;
    }
    private byte[] toEncrypt(Object o){

        //object内容进行加密
        return o.toString().getBytes();
    }
}
