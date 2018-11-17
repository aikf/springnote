package com.xym.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName LogBefore
 * @Description TODO:
 * @Author ak
 * @Date 2018/11/14 下午5:18
 * @Version 1.0
 **/
public class LogBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("[接口形式]前置通知...");
    }
}