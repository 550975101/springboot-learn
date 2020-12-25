package com.zihexin.course.controller;

import com.zihexin.course.entity.JsonResult;
import com.zihexin.course.entity.User;
import com.zihexin.course.exception.BusinessErrorException;
import com.zihexin.course.exception.BusinessMsgEnum;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

  /**
   * {
   *     "username":"123",
   *     "password":"123"
   * }
   * @param user
   * @return
   */
  @PostMapping("/testNullPointException")
  public JsonResult testNullPointException(@RequestBody User user) {
    //模拟空指针
    user.getId().equals(1);
    return new JsonResult();
  }

  @GetMapping("/business")
  public JsonResult testException() {
    try {
      int i = 1 / 0;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
    }
    return new JsonResult();
  }
}
