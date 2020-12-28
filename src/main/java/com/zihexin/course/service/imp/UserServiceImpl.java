package com.zihexin.course.service.imp;

import com.zihexin.course.dao.UserMapper;
import com.zihexin.course.entity.User;
import com.zihexin.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 封心
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserByName(String username) {
    return userMapper.getUserByName(username);
  }

  @Override
  public User getUserByIdAndName(Long id, String userName) {
    return userMapper.getUserByIdAndName(id, userName);
  }

  @Override
  @Transactional
  public Integer insertUser(User user) {
    Integer num = 0;
    num =userMapper.insertUser(user);
    int i = 1 / 0;
    return num;
  }
}
