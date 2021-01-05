package com.zihexin.course.interceptor;

import com.zihexin.course.exceptionHandler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 定义拦截器，只需要实现 HandlerInterceptor 接口，
 * HandlerInterceptor 接口是所有自定义拦截 器或者 Spring Boot 提供的拦截器的鼻祖，
 * 所以，首先来了解下该接口。该接口中有三个方法： preHandle(……) 、 postHandle(……) 和 afterCompletion(……) 。
 * preHandle(……) 方法：该方法的执行时机是，当某个 url 已经匹配到对应的 Controller 中的某 个方法，且在这个方法执行之前。
 * 所以 preHandle(……) 方法可以决定是否将请求放行，这是通 过返回值来决定的，返回 true 则放行，返回 false 则不会向后执行。
 *
 * postHandle(……) 方法：该方法的执行时机是，当某个 url 已经匹配到对应的 Controller 中的某 个方法，且在执行完了该方法，
 * 但是在 DispatcherServlet 视图渲染之前。所以在这个方法中有 个 ModelAndView 参数，可以在此做一些修改动作。
 *
 * afterCompletion(……) 方法：顾名思义，该方法是在整个请求处理完成后（包括视图渲染）执 行，
 * 这时做一些资源的清理工作，这个方法只有在 preHandle(……) 被成功执行后并且返回 true 才会被执行
 */
public class MyInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Method method = handlerMethod.getMethod();
    String methodName = method.getName();
    logger.info("===拦截到了方法:{},在该方法执行之前执行===", methodName);
    //返回true才会继续执行,返回false则取消当前请求
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了");
  }
}
