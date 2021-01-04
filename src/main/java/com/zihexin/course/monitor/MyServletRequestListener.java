package com.zihexin.course.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 封心
 */
@Component
public class MyServletRequestListener implements ServletRequestListener {

  private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    logger.info("session id为: {}", request.getRequestedSessionId());
    logger.info("request url为: {}", request.getRequestURL());
    request.setAttribute("name", "王某");
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    logger.info("request end");
    HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    logger.info("request 域中保存得name值为: {}", request.getAttribute("name"));
  }
}
