package com.zihexin.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这里有个问题需要注意一下，前面的课程中我们说了微服务中会走向前后端分离，
 * 我们在 Controller 层上都是使用的 @RestController 注解，自动会把返回的数据转成 json 格式。
 * 但是在使 用模板引擎时，Controller 层就不能用 @RestController 注解了，
 * 因为在使用 thymeleaf 模板时，返 回的是视图文件名，
 * 比如上面的 Controller 中是返回到 index.html 页面，
 * 如果使用 @RestController 的话，会把 index 当作 String 解析了，直接返回到页面了，
 * 而不是去找 index.html 页面，大家可以试一下。所以在使用模板时要用 @Controller 注解。
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

  @RequestMapping("/test404")
  public String test() {
    return "index";
  }

  @RequestMapping("/test500")
  public String test500() {
    int i = 1 / 0;
    return "index";
  }
}
