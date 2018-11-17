## 七、基于注解形式的AOP实现
1. 导入jar包(与实现接口的方式相同)
2. 配置
将业务类、通知 纳入SpringIOC容器<br>
使用注解形式将对象增加到IOC容器需开启注解扫描器`<context:component-scan base-package="com.xym.dao,com.xym.aop"/>`<br>
扫描器会将指定包中@Component@Servie@Respository@Controller修饰的类产生的对象加入IOC容器<br>
@Aspect不需要加入扫描器，只需在配置文件中开启即可<br>
开启注解对AOP的支持`<aop:aspectj-autoproxy/>`
3. 编写代码

通过注解形式实现的AOP，如果想获取目标对象的一些参数，则需要使用一个对象：JointPoint<br>
注解形式的返回值：需要在注解上声明返回值的参数名<br>
*LogAspectAnnotation.java*
```java
package com.xym.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

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
        System.out.println("Myafter注解形式《最终通知》");
    }
}
```
*applicationContext.xml*
```xml
    <!--配置扫描器-->
    <context:component-scan base-package="com.xym.dao,com.xym.aop"/>
    <!--开启注解对AOP的支持-->
    <aop:aspectj-autoproxy/>
```