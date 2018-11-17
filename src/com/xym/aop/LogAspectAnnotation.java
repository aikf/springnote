package com.xym.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName LogAspectAnnotation
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 下午8:33
 * @Version 1.0
 **/

@Component("logAnnotation")
@Aspect //声明该类是一个通知
public class LogAspectAnnotation {

    //前置通知
    @Before("execution(public * addStudent(..))") //属性：定义切点
    public void myBefore(JoinPoint jp){
        System.out.println("《注解形式-前置通知》目标对象："+jp.getTarget()+",方法名："+jp.getSignature().getName()+",参数列表："+ jp.getArgs().length);
    }

    //后置通知
    @AfterReturning(pointcut = "execution(public * addStudent(..))",returning = "returningValue")
    public void myAfter(JoinPoint jp,Object returningValue){
        System.out.println("《注解形式-后置通知》目标对象："+jp.getTarget()+",方法名："+jp.getSignature().getName()+",参数列表："+ Arrays.toString(jp.getArgs())+",返回值："+returningValue);
    }

    //环绕通知
    @Around("execution(public * addStudent(..))")
    public boolean myAround(ProceedingJoinPoint jp){
        boolean result = false;
        //方法执行前：前置通知
        System.out.println("《[注解环绕]方法之前-前置通知》");
        try{
            //方法执行时
            result = (boolean) jp.proceed();
            //方法执行后：后置通知
            System.out.println("《[注解环绕]方法之后-后置通知》");
        }catch (Throwable e){
            //发生异常时：异常通知
            System.out.println("《[注解环绕]发生异常时-异常通知》");
        }finally {
            //最终通知
            System.out.println("《[注解环绕]最终通知》");
        }
        return result;
    }

    //异常通知：如果只捕获类型的已知异常，则可以通过第二个参数实现
    @AfterThrowing(pointcut = "execution(public * addStudent(..))",throwing = "e")
    public void myException(JoinPoint jp,ArithmeticException e){
        System.out.println(e.getMessage());
        System.out.println("《注解形式-异常通知》");
    }
    //最终通知
    @After("execution(public * addStudent(..))")
    public void myAfter(){
        System.out.println("《myAfter注解形式-最终通知》");
    }
}