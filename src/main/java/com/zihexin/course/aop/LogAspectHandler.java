package com.zihexin.course.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 封心
 * @Aspect 注解用来描述一个切面类，定义切面类的时候需要打上这个注解。
 * @Component 注解让该类 交给 Spring 来管理
 *
 * 1.@Pointcut：定义一个切面，即上面所描述的关注的某件事入口。
 * 2.@Before：在做某件事之前做的事。
 * 3.@After：在做某件事之后做的事。
 * 4.@AfterReturning：在做某件事之后，对其返回值做增强处理。
 * 5.@AfterThrowing：在做某件事抛出异常时，处理。
 */
@Aspect
@Component
public class LogAspectHandler {

  private final Logger logger = LoggerFactory.getLogger(LogAspectHandler.class);

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * @Pointcut 注解指定一个切面，定义需要拦截的东西，这里介绍两个常用的表达式：
   * 一个是使用 execution() ，另一个是使用 annotation() 。
   * 以 execution(* com.itcodai.course09.controller..*.*(..))) 表达式为例，语法如下：
   * execution() 为表达式主体 第一个 * 号的位置：表示返回值类型， * 表示所有类型 包名：表示需要拦截的包名，
   * 后面的两个句点表示当前包和当前包的所有子包，
   * com.itcodai.course09.controller 包、子包下所有类的方法
   * 第二个 * 号的位置：表示类名， * 表示所有类 *(..) ：这个星号表示方法名， * 表示所有的方法，
   * 后面括弧里面表示方法的参数，两个句点 表示任何参数
   */
  @Pointcut("execution(* com.zihexin.course.controller..*.*(..))")
  public void pointCut() {
  }

  /**
   * annotation() 方式是针对某个注解来定义切面，比如我们对具有 @GetMapping 注解的方法做切面， 可以如下定义切面
   */
  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  public void annotationCut() {
  }

  /**
   * 在上面定义的切面方法之前 执行该方法
   */
  @Before("pointCut()")
  public void doBefore(JoinPoint joinPoint) {
    logger.info("====doBefore方法进入了====");

    //获取签名
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    //获取切入的包名
    String declaringTypeName = signature.getDeclaringTypeName();
    //获取即将执行的方法
    Method method = signature.getMethod();
    logger.info("即将执行方法为:{},属于{}包", method.getName(), declaringTypeName);

    //获取请求的类名
    String className = joinPoint.getTarget().getClass().getName();
    //获取请求方法名
    String methodName = method.getName();
    methodName = className + "." + methodName;
    //也可以用来记录一些信息 比如请求url和ip
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    //获取请求url
    String url = request.getRequestURL().toString();
    //获取请求ip
    String ip = request.getRemoteAddr();
    //请求参数
    Map<String, String> rtnMap = converMap(request.getParameterMap());
    try {
      logger.info("用户请求的URL为{},ip地址为:{},请求参数{}", url, ip, objectMapper.writeValueAsString(rtnMap));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public Map<String, String> converMap(Map<String, String[]> paramMap) {
    HashMap<String, String> rtnMap = Maps.newHashMap();
    for (String key : paramMap.keySet()) {
      rtnMap.put(key, paramMap.get(key)[0]);
    }
    return rtnMap;
  }
}
