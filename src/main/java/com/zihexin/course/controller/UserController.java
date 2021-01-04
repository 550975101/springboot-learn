package com.zihexin.course.controller;

import com.zihexin.course.entity.User;
import com.zihexin.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/request")
  public String getRequestInfo() {
    User user2 = userService.getUser2();
    return user2.getUsername();
  }
}
