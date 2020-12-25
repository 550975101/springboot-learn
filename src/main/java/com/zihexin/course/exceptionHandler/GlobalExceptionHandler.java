package com.zihexin.course.exceptionHandler;

import com.zihexin.course.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  @ControllerAdvice
 *  注解可以看到， @ControllerAdvice 注解包含了 @Component 注 解，
 *  说明在 Spring Boot 启动时，也会把该类作为组件交给 Spring 来管理。
 *  除此之外，该注解还有个 basePackages 属性，该属性是用来拦截哪个包中的异常信息，
 *  一般我们不指定这个属性，我们拦截项 目工程中的所有异常。
 *  @ResponseBody 注解是为了异常处理完之后给调用方输出一个 json 格式的封装 数据。
 * @author 封心
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 缺少请求参数异常
   * @param ex
   * @return
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public JsonResult handleHttpMessageNotReadableException(MissingServletRequestParameterException ex) {
    logger.error("缺少请求参数,{}", ex.getMessage());
    return new JsonResult("400", "缺少必要请求参数");
  }
}
