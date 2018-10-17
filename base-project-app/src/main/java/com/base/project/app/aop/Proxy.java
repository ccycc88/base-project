package com.base.project.app.aop;

import com.base.project.commcon.annotation.aop.Param;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;

@Component
@Aspect
public class Proxy {

    @Pointcut("execution(public * com.base.project.api..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        System.out.println("URL : " + request.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + request.getMethod());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//
//        System.out.println(joinPoint.getTarget());
//        System.out.println(joinPoint.getKind());
////        System.out.println(joinPoint.getSourceLocation().getFileName());
//        System.out.println(joinPoint.getThis());
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//
//        for(Annotation[] annotation : joinPoint.getSignature().getDeclaringType().getDeclaredMethods()[0].getParameterAnnotations()){
//
//            //Param[] params = annotation.getAnnotationsByType(Param.class);
//            //Param[] p = annotation.getDeclaredAnnotationsByType(Param.class);
//           // System.out.println(p.length);
//            Param p = (Param) annotation[0];
//            System.out.println(p.value());
//        }
        //joinPoint.getSignature().getDeclaringType().getDeclaredMethods()[0].getAnnotatedParameterTypes()

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void afterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void afterThrowing(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        return pjp.proceed();
    }
    private void get(Class<?> clz){

        
    }
}
