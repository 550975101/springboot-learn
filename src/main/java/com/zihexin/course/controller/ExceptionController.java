package com.zihexin.course.controller;

import com.zihexin.course.entity.JsonResult;
import com.zihexin.course.exceptionHandler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 封心
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

  @PostMapping("/test")
  public JsonResult test(@RequestParam("name") String name, @RequestParam String password) {
    logger.info("name: {}", name);
    logger.info("password: {}", password);
    return new JsonResult();
  }
}
