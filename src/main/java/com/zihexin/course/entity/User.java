package com.zihexin.course.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author 封心
 * @ApiModel 注解用于实体类，表示对类进行说明，用于参数用实体类接收。
 * @ApiModelProperty 注解用于类中属性，表示对 model 属性的说明或者数据操作更改。
 */
@ApiModel(value = "用户实体类")
public class User {

  @ApiModelProperty(value = "用户唯一标识")
  private Long id;

  @ApiModelProperty(value = "用户姓名")
  private String username;

  @ApiModelProperty(value = "用户密码")
  private String password;

  public User() {
  }

  public User(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      '}';
  }

  public static void main(String[] args) {
    User user = new User();
    user.setId(1L);
    user.setUsername("啦啦啦");
    user.setPassword("123123");
    String s = JSONObject.toJSONString(user);
    System.out.println("对象转json字符串" + s);

    User user1 = JSONObject.parseObject(s, User.class);
    System.out.println("json解析成对象" + user1);

    Map map = JSONObject.parseObject(s, Map.class);
    System.out.println(map.get("id"));
    System.out.println(map.get("username"));
    System.out.println(map.get("password"));
  }
}
