## 八、基于Schema形式的AOP实现
基本Schema配置(XML)类似于实现接口的方式
接口方式通知：需要类实现接口如：`public class LogAfter implements AfterReturningAdvice{}`
Schema方式通知：a.编写一个普通类`public class LogAfter{}`b.将该类通过配置转为一个通知
要获取目标对象信息:
注解、Schema:JoinPoint
接口：Method method, Object[] args, Object target
*LogSchema.java*
```java
package com.xym.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import java.util.Arrays;

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
```
*applicationContext.xml*
```xml
    <!--Schema方式-->
    <bean id="logSchema" class="com.xym.aop.LogSchema"/>
    <aop:config>
        <aop:pointcut id="s1" expression="execution(public * com.xym.service.StudentServiceImpl.*(..))"/>
        <aop:aspect ref="logSchema">
            <aop:before method="before" pointcut-ref="s1"/>
            <aop:after-returning method="afterReturning" pointcut-ref="s1" returning="returnValue"/>
            <aop:after-throwing method="whenException" pointcut-ref="s1" throwing="e"/>
            <aop:around method="around" pointcut-ref="s1"/>
        </aop:aspect>
    </aop:config>
```