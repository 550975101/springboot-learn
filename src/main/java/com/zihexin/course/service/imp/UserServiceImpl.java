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

  /**
   * ？因为 Spring Boot 默认的事务规则是遇到运行异常（RuntimeException）和程序 错误（Error）才会回滚。
   * 比如上面我们的例子中抛出的 RuntimeException 就没有问题，但是抛出 SQLException 就无法回滚了。
   * 针对非运行时异常，如果要进行事务回滚的话，可以在 @Transactional 注解中使用 rollbackFor 属性来指定异常，
   * 比如 @Transactional(rollbackFor = Exception.class) ，这样就没有问题了，所以在实际项目中，一定要指定异常
   *
   * 有两种方式， 要么抛出去，让上一层来捕获处理；要么把异常 try catch 掉，在异常出现的地方给处理掉。
   * 就因为有 这中 try...catch，所以导致异常被 ”吃“ 掉，事务无法回滚
   * 因为我们的 思维很容易导致 try...catch 代码的产生，一旦出现这种问题，往往排查起来比较费劲，
   * 所以我们平时在 写代码时，一定要多思考，多注意这种细节，尽量避免给自己埋坑
   * @param user
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer insertUser(User user) {
    Integer num = 0;
    num =userMapper.insertUser(user);
    int i = 1 / 0;
    return num;
  }

  @Override
  public User getUser() {
    return new User(1L, "王某", "123456");
  }
}
