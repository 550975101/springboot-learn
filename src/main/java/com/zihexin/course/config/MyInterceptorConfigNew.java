package com.zihexin.course.config;

import com.zihexin.course.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 封心
 */
@Configuration
public class MyInterceptorConfigNew implements WebMvcConfigurer {

  /**
   * ，具体他们之间的细节，感兴趣的读者可以做进一步的研究，由于这两种方式的不 同，继承 WebMvcConﬁgurationSupport 类的方式可以用在前后端分离的项目中，
   *  后台不需要访问静 态资源（就不需要放开静态资源了）；实现 WebMvcConﬁgure 接口的方式可以用在非前后端分离的项 目中，
   *  因为需要读取一些图片、css、js文件等等。
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
  }
}
