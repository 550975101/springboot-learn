package com.zihexin.course.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 封心
 */
//@RestController 注解即可返回 Json 格式的数 据
//@RestController 注解包含了原来的 @Controller 和 @ResponseBody 注解
//@ResponseBody 注解是将返回 的数据结构转换为 Json 格式
//Spring Boot 中默认使用的 Json 解析技术框架是 jackson
@RestController
@RequestMapping("/start")
public class StartController {

  @RequestMapping("/springboot")
  public String startSpringBoot() {
    return "welcome to the world of Spring Boot";
  }
}
