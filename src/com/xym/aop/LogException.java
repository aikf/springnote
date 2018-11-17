package com.xym.aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName LogException
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 下午6:50
 * @Version 1.0
 **/
public class LogException implements ThrowsAdvice {
    //异常通知的具体方法
    public void afterThrowing(Method method, Object[] args, Object target, Throwable ex){
        System.out.println("[接口形式]异常通知：目标对象："+target+"，调用方法名："+method.getName()+"，方法参数个数："+args.length+"，异常类型："+ex.getMessage());

    }
}