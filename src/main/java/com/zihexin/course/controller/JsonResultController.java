package com.zihexin.course.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zihexin.course.entity.JsonResult;
import com.zihexin.course.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 封心
 */
@RestController
@RequestMapping("/jsonresult")
public class JsonResultController {

  @RequestMapping("/user")
  public JsonResult<User> getUser() {
    User user = new User(1L, "王某", "123456");
    return new JsonResult<>(user);
  }

  @RequestMapping("/list")
  public JsonResult<List> getUserList() {
    ArrayList<User> users = Lists.newArrayList();
    users.add(new User(1L, "王某", "123123"));
    users.add(new User(2L, "李某", "123123"));
    return new JsonResult<>(users, "获取用户列表成功");
  }

  @RequestMapping("/map")
  public JsonResult<Map> getMap() {
    HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(3);
    User user = new User(1L, "王某", "123123");
    map.put("作者信息", user);
    map.put("博客地址", "www.baidu.com");
    map.put("CSDN地址", null);
    map.put("粉丝数量", 4153);
    return new JsonResult<>(map);
  }

}
