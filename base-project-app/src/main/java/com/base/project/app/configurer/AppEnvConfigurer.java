package com.base.project.app.configurer;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class AppEnvConfigurer {

    @Bean
    public MultipartResolver multipartResolver() {

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        resolver.setDefaultEncoding("UTF-8");
        //resolver.setMaxInMemorySize(memorySize);
        resolver.setResolveLazily(true);
        resolver.setMaxUploadSize(10485760000l);
        return resolver;
    }

    /**
     * 为特定过滤器设置对应要过滤的url
     * @return
     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//
//        bean.setFilter(null);
//        bean.setUrlPatterns(null);
//        return bean;
//    }
}
