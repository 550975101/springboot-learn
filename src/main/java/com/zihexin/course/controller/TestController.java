package com.zihexin.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author 封心
 */
@RestController
@RequestMapping(value = "/test", produces = "application/json; charset=UTF-8")
//@RequestMapping 是一个用来处理请求地址映射的注解，它可以用于类上，也可以用于方法上。
// 在类 的级别上的注解会将一个特定请求或者请求模式映射到一个控制器之上，
// 表示类中的所有响应请求的方 法都是以该地址作为父路径；在方法的级别表示进一步指定到处理方法的映射关系。
//该注解有6个属性，一般在项目中比较常用的有三个属性：value、method 和 produces。
//value 属性：指定请求的实际地址，value 可以省略不写 method 属性：指定请求的类型，
// 主要有 GET、PUT、POST、DELETE，默认为 GET produces属性：指定返回内容类型，
// 如 produces = "application/json; charset=UTF-8"
public class TestController {

  private final static Logger logger = LoggerFactory.getLogger(TestController.class);

  @RequestMapping("/log")
  public String testLog() {
    logger.debug("======测试日志debug级别=======");
    logger.info("======测试日志info级别=======");
    logger.error("======测试日志error级别=======");
    logger.warn("======测试日志warn级别=======");
    //可以使用占位符 打印一些参数信息
    String str1 = "www.baidu.com";
    String str2 = "www.baidu.com";
    logger.info("========王某个人博客地址:{};王某的CSDN:{}", str1, str2);
    return "success";
  }

  @RequestMapping(value = "/get",method = RequestMethod.GET)
  public String testGet() {
    return "success";
  }

  //@PathVariable 注解主要是用来获取 url 参数，
  //Spring Boot 支持 restfull 风格的 url，比如一个 GET 请求携带一个参数 id 过来，
  //我们将 id 作为参数接收，可以使用 @PathVariable 注解
//  @GetMapping("/user/{id}")
//  public String testPathVariable(@PathVariable Integer id) {
//    logger.info("获取到的id为:{}", id);
//    return "success" + id;
//  }

  @GetMapping("/user/{idd}")
  public String testPathVariableSetValue(@PathVariable(value = "idd") Integer id) {
    logger.info("获取到的idd为:{}", id);
    return "success" + id;
  }

  @GetMapping("/user/{idd}/{name}")
  public String testPathVariable(@PathVariable(value = "idd") Integer id, @PathVariable String name) {
    logger.info("获取到的id为:{}", id);
    logger.info("获取到的name为:{}", name);
    return "success";
  }
}
