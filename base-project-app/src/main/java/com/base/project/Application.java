package com.base.project;

import com.base.project.app.listener.PreparedListener;
import com.base.project.app.listener.ReadyListener;
import com.base.project.commcon.constant.SysEnvVar;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    public static void  main(String[] args){

        SpringApplication application = new SpringApplication(Application.class);
        //application.setAdditionalProfiles("dev");
        application.setBannerMode(Banner.Mode.OFF);
        application.addListeners(new PreparedListener());
        application.addListeners(new ReadyListener());
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(Application.class).listeners(new PreparedListener()).listeners(new ReadyListener()).bannerMode(Banner.Mode.OFF);
    }
    @Bean
    public Docket createSwagger2Api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swagger2Api())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.base.project.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo swagger2Api() {

        return new ApiInfoBuilder()
                .title("spring boot 项目构建基础工程")
                .description("base-project提供基于spring boot 开发项目丰富的基础能力")
                .contact(new Contact("一诺","https://blog.csdn.net/ccycc88", "ccycc88@163.com"))
                .version(SysEnvVar.SYS_VERSION)
                .build();
    }
}
