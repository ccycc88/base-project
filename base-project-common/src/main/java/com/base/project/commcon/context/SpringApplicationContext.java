package com.base.project.commcon.context;

import org.springframework.context.ApplicationContext;

public class SpringApplicationContext {
    private static ApplicationContext appliction = null;
    private static String active = null;

    public static ApplicationContext getAppliction() {
        return appliction;
    }

    public static void setAppliction(ApplicationContext appliction) {
        SpringApplicationContext.appliction = appliction;
    }
    public static <T> T getBean(Class<T> clz) {

        return appliction.getBean(clz);
    }
    public static Object getBean(String beanName) {

        return appliction.getBean(beanName);
    }

    public static String getActive() {
        return active;
    }

    public static void setActive(String active) {
        SpringApplicationContext.active = active;
    }
}
