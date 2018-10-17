package com.base.project.api.externalized;

import com.base.project.commcon.annotation.aop.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可以通过spring 的环境变量获取变量信息
 */
@RestController
public class ExternalizedAction {

    @Autowired
    private Environment environment;

    @GetMapping("/env")
    public String getEnv(@Param("str") String str){

        System.out.println(str);
        environment.getActiveProfiles();
        return "1234";
    }
}
