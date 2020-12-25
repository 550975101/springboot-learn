package com.zihexin.course.exceptionHandler;

import com.zihexin.course.entity.JsonResult;
import com.zihexin.course.exception.BusinessErrorException;
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

  /**
   * 空指针异常了
   * @param ex
   * @return
   */
  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult handleTypeMismatchException(NullPointerException ex) {
    logger.error("空指针异常，{}", ex.getMessage());
    return new JsonResult("500", "空指针异常了");
  }

  @ExceptionHandler(BusinessErrorException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult handleBusinessError(BusinessErrorException e) {
    String code = e.getCode();
    String msg = e.getMessage();
    return new JsonResult(code, msg);
  }

  /**
   * 系统异常  预期之外异常
   *当然了，异常很多，比如还有 RuntimeException，数据库还有一些查询或者操作异常等等。
   * 由于 Exception 异常是父类，所有异常都会继承该异常，
   * 所以我们可以直接拦截 Exception 异常，一劳永 逸：
   *
   * 但是项目中，我们一般都会比较详细的去拦截一些常见异常，拦截 Exception
   * 虽然可以一劳永逸，但是 不利于我们去排查或者定位问题。
   * 实际项目中，可以把拦截 Exception 异常写在 GlobalExceptionHandler
   * 下面，如果都没有找到，后再拦截一下 Exception 异常，保证输出信息 友好。
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult handleUnexpectedServer(Exception e) {
    logger.error("系统异常", e);
    return new JsonResult("500", "系统发生异常,请联系管理员");
  }
}
