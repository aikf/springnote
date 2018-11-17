package com.xym.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import java.util.Arrays;

/**
 * @ClassName LogSchema
 * @Description TODO
 * @Author ak
 * @Date 2018/11/15 上午9:04
 * @Version 1.0
 **/
public class LogSchema {

    public void afterReturning(JoinPoint jp, Object returnValue) {
        System.out.println("[Schema形式]后置通知：目标对象："+jp.getTarget()+",方法名："+jp.getSignature().getName()+",参数列表："+ Arrays.toString(jp.getArgs())+",返回值："+returnValue);
    }

    public void before(){
        System.out.println("[Schema形式]前置通知。。。");
    }

    public void whenException(JoinPoint jp, ArithmeticException e) {
        System.out.println("[Schema形式]异常通知。。。"+e.getMessage());
    }

    //环绕通知会返回目标方法的返回值
    public boolean around(ProceedingJoinPoint jp) {
        boolean result = false;
        System.out.println("[Schema形式]环绕前置通知。。。");
        try{
            result = (boolean) jp.proceed();
            System.out.println(jp.getTarget()+jp.getSignature().getName()+jp.getArgs().length);
            System.out.println("[Schema形式]环绕后置通知。。。");
        }catch (Throwable e){
            System.out.println("[Schema形式]环绕异常通知。。。");
        }
        return result;
    }
}