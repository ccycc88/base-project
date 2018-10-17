package com.base.project.app.configurer;

import com.base.project.app.interceptor.AppInterceptor;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class AppMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AppInterceptor appInterceptor;
    /**
     * 跨域访问配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/webapp/**")
                .addResourceLocations("classpath:/webapp/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    /**
     * 异步请求配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

        //configurer.
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    /**
     * 注册拦截器
     * 拦截器的执行将按照添加的顺序执行
     * @param interceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

        interceptorRegistry.addInterceptor(appInterceptor).addPathPatterns("/*");
    }

    /**
     * json转换器使用GsonHttpMessageConverter
     * jackson json多层处理不了
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Json.class, new JsonSerializer<Json>() {
                    @Override
                    public JsonElement serialize(Json json, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonParser parser = new JsonParser();
                        return parser.parse(json.value());
                    }
                })
                .create();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson);
        converters.add(gsonHttpMessageConverter);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

}
