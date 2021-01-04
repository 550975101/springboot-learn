package com.zihexin.course.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 封心
 *
 * 使用监听器获取用户的访问信息比较简单，实现 ServletRequestListener 接口即可
 */
@Component
public class MyServletRequestListener implements ServletRequestListener {

  private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    logger.info("session id为: {}", request.getRequestedSessionId());
    logger.info("request url为: {}", request.getRequestURL());
    request.setAttribute("name", "王某");
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    logger.info("request end");
    HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    logger.info("request 域中保存得name值为: {}", request.getAttribute("name"));
  }
}
