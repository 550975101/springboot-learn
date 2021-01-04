package com.zihexin.course.monitor;

import com.zihexin.course.entity.User;
import com.zihexin.course.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;


/**
 * @author 封心
 * 写一个监听器，实现 ApplicationListener<ContextRefreshedEvent> 接口，重写 onApplicationEvent 方法，
 * 将 ContextRefreshedEvent 对象传进去。如果我们想在加载或刷新应用 上下文时，
 * 也重新刷新下我们预加载的资源，就可以通过监听 ContextRefreshedEvent 来做这样的事 情。
 */
@Component
public class MyServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    //获取applicationContext上下文
    ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
    //获取对应的service
    UserService userService = applicationContext.getBean(UserService.class);
    User user = userService.getUser();
    //获取applicationContext域域对象
    ServletContext servletContext = applicationContext.getBean(ServletContext.class);
    servletContext.setAttribute("user", user);
  }
}
