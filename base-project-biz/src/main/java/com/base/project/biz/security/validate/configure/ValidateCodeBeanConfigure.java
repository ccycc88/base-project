package com.base.project.biz.security.validate.configure;

import com.base.project.biz.security.validate.generate.achieve.ImageValidateCodeGenerator;
import com.base.project.biz.security.validate.generate.achieve.SmsValidateCodeGenerator;
import com.base.project.biz.security.validate.processor.achieve.ImageValidateCodeProcessor;
import com.base.project.biz.security.validate.processor.achieve.SmsValidateCodeProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置名字需要做缓存key配置
 */
@Configuration
public class ValidateCodeBeanConfigure {

    /**
     * 生成验证码生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="imageValidateCodeGenerator")
    public ImageValidateCodeGenerator imageValidateCodeGenerator(){

        ImageValidateCodeGenerator generator = new ImageValidateCodeGenerator();

        return generator;
    }

    /**
     * 验证码处理器，用于验证码的生成存储，验证等
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="imageValidateCodeProcessor")
    public ImageValidateCodeProcessor imageValidateCodeProcessor(){

        ImageValidateCodeProcessor processor = new ImageValidateCodeProcessor();

        return processor;
    }

    /**
     * 短信验证码生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="smsValidateCodeGenerator")
    public SmsValidateCodeGenerator smsValidateCodeGenerator(){

        SmsValidateCodeGenerator generator = new SmsValidateCodeGenerator();
        return generator;
    }

    /**
     * 短信验证码处理器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="smsValidateCodeProcessor")
    public SmsValidateCodeProcessor smsValidateCodeProcessor(){

        SmsValidateCodeProcessor processor = new SmsValidateCodeProcessor();
        return processor;
    }
}
