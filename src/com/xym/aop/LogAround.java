package com.xym.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @ClassName LogAround
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 下午7:12
 * @Version 1.0
 **/
public class LogAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        //方法1...
        try {
            //方法2...

            //invocation.proceed()之前的代码：前置通知
            System.out.println("[接口形式]环绕前置通知：目标对象："+invocation.getThis()+"，调用方法名："+invocation.getMethod().getName()+"，方法参数个数："+invocation.getArguments()+"，返回值："+result);
            result = invocation.proceed(); //控制目标方法的执行
            //invocation.proceed()之后的代码：后置通知
            System.out.println("[接口形式]环绕后置通知...");
        } catch (Exception e) {
            //方法3...
            //异常通知
            System.out.println("[接口形式]环绕异常通知...");

        }
        return "abc"; //目标方法的返回值
    }
}