package com.zihexin.course.service;

import com.zihexin.course.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 封心
 */
public interface UserService {

  User getUserByName(String username);

  User getUserByIdAndName(Long id, String  userName);

  Integer insertUser(User user);

  User getUser();
}
