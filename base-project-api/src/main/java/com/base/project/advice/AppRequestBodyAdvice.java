package com.base.project.advice;

import com.base.project.commcon.annotation.des.DecryptRequestBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 这里只针对application/json的数据进行请求解密
 */
@ControllerAdvice
public class AppRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {

        if(GsonHttpMessageConverter.class.isAssignableFrom(aClass)){

            return true;
        }
        return false;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {

        return o;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        boolean dencrypt = false;
        HttpInputMessage returnInputMessage = null;

        if(methodParameter.getMethod().isAnnotationPresent(DecryptRequestBody.class)) {

            DecryptRequestBody body = methodParameter.getMethodAnnotation(DecryptRequestBody.class);
            dencrypt = body.dencrypt();
        }
        if(dencrypt) {

            InputStream is = httpInputMessage.getBody();

            //在此处对数据进行解密

            returnInputMessage = new DecryptHttpInputMessage(httpInputMessage.getHeaders(), this.toDencrypt(is));
            //returnInputMessage =
        }else {

            returnInputMessage = httpInputMessage;
        }

        return returnInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {

        return o;
    }

    public InputStream toDencrypt(InputStream is) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[4096];
        int n = 0;
        while ((n = is.read(b)) > 0) {
            out.write(b, 0, n);
        }
        byte[] buf = out.toByteArray();

        //对buf进行解密
        //
        ByteArrayInputStream bis = new ByteArrayInputStream(buf);
        return bis;
    }
    class DecryptHttpInputMessage implements HttpInputMessage{

        private HttpHeaders headers = null;
        private InputStream is = null;

        public DecryptHttpInputMessage(HttpHeaders headers, InputStream is) {

            this.headers = headers;
            this.is = is;
        }
        @Override
        public HttpHeaders getHeaders() {
            // TODO Auto-generated method stub
            return headers;
        }

        @Override
        public InputStream getBody() throws IOException {
            // TODO Auto-generated method stub
            return is;
        }


    }
}
