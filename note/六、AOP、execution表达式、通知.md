## 六、AOP、execution表达式、通知

AOP:面向方面编程<br>
一个普通的类  --> 有特定功能的类<br>
a.继承类 b.实现接口 c.注解 d.配置
#### AOP操作术语
- Joinpoint(连接点):指那些被拦截到的点，在Spring中，这些点指的是方法，因为Spring只支持方法类型的连接点
- Pointcut(切入点):指我们要对哪些Joinpoint进行拦截的定义
- Advice(通知/增强):指拦截到Joinpoint后要做的事情，通知分为前置通知，后置通知，异常通知，最终通知，环绕通知(切面要完成的功能)
- Aspect(切面):是切入点和通知(引介)的结合

另一种解释
- Joinpoint:类里面可以被增强的方法，这些方法称为连接点
- Pointcut:在类里面可以有很多方法被增加，比如实际操作中，只是增强了类里的add方法和update方法，实际增强的方法称为切入点
- Advice:增加的逻辑，称为增强，比如扩展日志功能，这个日志功能称为增强
  - 前置通知：在方法之前执行
  - 后置通知：在方法之后执行
  - 异常通知：在方法出面异常
  - 最终通知：在后置之后执行
  - 环绕通知：在方法前后执行
- Aspect:把增强应用到具体方法上面，这个过程称为切面

**xml方式的通知类型**<br>
| 通知类型 | 需要实现的接口 | 接口中的方法 | 执行时机 |
:- | :- | :- | :-
前置通知 | org.springframework.aop.MethodBeforeAdvice | before() | 目标方法执行前
后置通知 | org.springframework.aop.AfterReturningAdvice | afterReturning() | 目标方法执行后
异常通知 | org.springframework.aop.ThrowsAdvice | 无 | 目标方法发生异常时
环绕通知 | org.aopalliance.intercept.MethodInterceptor | invoke() | 拦截对目标方法的调用，即调用目标方法的整个过程

#### 前置通知
1. 导入jar包

aspectjweaver.jar

2. 编写业务类、通知类

每当执行add()之前 自动执行一个方法<br>
add();  业务方法(IStudentService.java中的addStudent())<br>
log();  自动执行的通知，即AOP前置通知<br>
*LogBefore.java*
```java
package com.xym.aop;

import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

public class LogBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("前置通知。。。");
    }
}
```
3. 配置

- 将业务类，通知类纳入IOC容器
- 定义切入点(一端)、定义通知类(另一端),通过poincut-ref将两端连接起来

*applicationContext.java*
```xml
    <!--addStudent()所在类-->
    <bean id="studentService" class="com.xym.service.StudentServiceImpl">
        <property name="studentDao" ref="studentDao"/>
    </bean>
    <!--前置通知类-->
    <!--=====连接线的一方=====-->
    <bean id="logBefore" class="com.xym.aop.LogBefore"/>
    <!--将addStudent()和通知进行关联-->
    <aop:config>
        <!--=====连接线的另一方=====-->
        <aop:pointcut id="p1" expression="execution(public void com.xym.service.StudentServiceImpl.addStudent(com.xym.entities.Student))"/>
        <!--=====连接线=====-->
        <aop:advisor advice-ref="logBefore" pointcut-ref="p1" />
    </aop:config>
```

#### 后置通知
*LogAfter.java*
```java
public class LogAfter implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("目标对象："+target+"，调用方法名："+method.getName()+"，方法参数个数："+args.length+"，方法的返回值："+returnValue);
    }
}
```
*applicationContext.java*
```xml
    <bean id="logAfter" class="com.xym.aop.LogAfter"/>
    <aop:config>
        <aop:pointcut id="p2" expression="execution(public void com.xym.service.StudentServiceImpl.*(..))"/>
        <aop:advisor advice-ref="logAfter" pointcut-ref="p2" />
    </aop:config>
```
#### 异常通知
根据异常通知的接口定义可以发现，异常通知的实现类必须编写以下方法<br>
```public void afterThrowing([Method, args, target], ThrowableSubclass)```<br>
该方法有两种情况<br>
a. public void afterThrowing(Method, args, target, ThrowableSubclass)<br>
b. public void afterThrowing(ThrowableSubclass)<br>
*LogException.java*
```java
public class LogException implements ThrowsAdvice {
    //异常通知的具体方法
    public void afterThrowing(Method method, Object[] args, Object target, Throwable ex){
        System.out.println("目标对象："+target+"，调用方法名："+method.getName()+"，方法参数个数："+args.length+"，异常类型："+ex.getMessage());

    }
}
```
*applicationContext.java*
```xml
    <bean id="logException" class="com.xym.aop.LogException"/>
    <aop:config>
        <aop:pointcut id="p3" expression="execution(public void com.xym.service.StudentServiceImpl.*(..))"/>
        <aop:advisor advice-ref="logException" pointcut-ref="p3" />
    </aop:config>
```
#### 环绕通知
在目标方法的前后、异常发生时、最终等各个地方都可以进行的通知，最强大的一个通知，可以获得目标方法的全部控制权(目标方法是否执行、执行前、执行后、参数、返回值等)<br>
在使用环绕通知时，目标方法的一切信息都可以通过invocation参数获取<br>
环绕通知底层是通过拦截器实现的<br>
*LogAround.java*
```java
public class LogAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        //方法1...
        try {
            //方法2...

            //invocation.proceed()之前的代码：前置通知
            System.out.println("用环绕通知实现的[前置通知]...");
            result = invocation.proceed(); //控制目标方法的执行
            //invocation.proceed()之后的代码：后置通知
            System.out.println("用环绕通知实现的[后置通知]...");
            System.out.println("环绕通知获取的目标对象："+invocation.getThis()+"，调用方法名："+invocation.getMethod().getName()+"，方法参数个数："+invocation.getArguments()+"，返回值："+result);

        } catch (Exception e) {
            //方法3...
            //异常通知
            System.out.println("用环绕通知实现的[异常通知]...");

        }
        return "abc"; //目标方法的返回值
    }
}
```
*applicationContext.xml*
```xml
    <bean id="logAround" class="com.xym.aop.LogAround"/>
    <aop:config>
        <aop:pointcut id="p4" expression="execution(public void com.xym.service.StudentServiceImpl.*(..))"/>
        <aop:advisor advice-ref="logAround" pointcut-ref="p4" />
    </aop:config>
```