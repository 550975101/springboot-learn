package com.zihexin.course.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
@RequestMapping("/json")
public class JsonController {

  @RequestMapping("/user")
  public User getUser() {
    return new User(1L, "王某", "123456");
  }

  @RequestMapping("/list")
  public List<User> getUserList() {
    ArrayList<User> users = Lists.newArrayList();
    users.add(new User(1L, "王某", "123123"));
    users.add(new User(2L, "李某", "123123"));
    return users;
  }

  @RequestMapping("/map")
  public Map<String, Object> getMap() {
    HashMap<String, Object> map = Maps.newHashMap();
    map.put("作者信息", new User(1L, "王某", "123123"));
    map.put("博客地址", "www.baidu.com");
    map.put("CSDN地址", "www.baidu.com");
    map.put("粉丝数量", 1000);
    return map;
  }

  @RequestMapping("/mapContainNull")
  public Map<String, Object> getMapContainNull() {
    HashMap<String, Object> map = Maps.newHashMap();
    map.put("作者信息", new User(1L, "王某", null));
    map.put("博客地址", "www.baidu.com");
    map.put("CSDN地址", "www.baidu.com");
    map.put("粉丝数量", null);
    return map;
  }
}
