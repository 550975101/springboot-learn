package com.zihexin.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 封心
 */
@RestController
@RequestMapping("/test")
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
}
