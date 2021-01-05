package com.zihexin.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

  @RequestMapping("/test")
  public String test() {
    return "hello";
  }
}
