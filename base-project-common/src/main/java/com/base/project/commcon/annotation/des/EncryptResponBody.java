package com.base.project.commcon.annotation.des;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptResponBody {

    boolean encrypt() default true;
}
