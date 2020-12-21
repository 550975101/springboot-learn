package com.zihexin.course.controller;

import com.zihexin.course.config.MicroServiceUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ConfigController {

  private final static Logger logger = LoggerFactory.getLogger(ConfigController.class);

  @Value("${url.orderUrl}")
  public String orderUrl;

  @Autowired
  private MicroServiceUrl microServiceUrl;

  @RequestMapping("/config")
  public String testConfig() {
    logger.info("获取订单的服务地址为:{}", orderUrl);
    return "success";
  }

  @RequestMapping("/configObject")
  public String testConfigObject() {
    logger.info("获取订单的服务地址为:{}", microServiceUrl.getOrderUrl());
    logger.info("获取用户的服务地址为:{}", microServiceUrl.getUserUrl());
    logger.info("获取购物车的服务地址为:{}", microServiceUrl.getShoppingUrl());
    return "success";
  }
}
