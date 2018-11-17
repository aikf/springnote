package com.xym.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName LogAfter
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 下午6:19
 * @Version 1.0
 **/
public class LogAfter implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("[接口形式]后置通知：目标对象："+target+"，调用方法名："+method.getName()+"，方法参数个数："+args.length+"，方法的返回值："+returnValue);
    }
}