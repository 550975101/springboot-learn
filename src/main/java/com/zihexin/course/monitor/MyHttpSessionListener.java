package com.zihexin.course.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 封心
 * 监听器还有一个比较常用的地方就是用来监听 session 对象，
 * 来获取在线用户数量，现在有很多开发者 都有自己的网站，
 * 监听 session 来获取当前在下用户数量是个很常见的使用场景，下面来介绍一下如何 来使用
 *
 * 使用HttpSessionListener统计在线用户数的监听器
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

  private static final Logger logger = LoggerFactory.getLogger(HttpSessionListener.class);

  public Integer count = 0;

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    logger.info("新用户上线了");
    count++;
    se.getSession().getServletContext().setAttribute("count", count);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    logger.info("用户下线了");
    count--;
    se.getSession().getServletContext().setAttribute("count", count);
  }
}
