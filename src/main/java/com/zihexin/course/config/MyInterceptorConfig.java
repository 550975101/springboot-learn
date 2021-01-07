//package com.zihexin.course.config;
//
//import com.zihexin.course.interceptor.MyInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * 我们都是直接继承 WebMvcConﬁgurerAdapter 类，然后重写 addInterceptors 方法来实现拦截器的配置。
// * 但是在 Spring Boot 2.0 之后，该方法已经被废弃了 （当然，也可以继续用），
// * 取而代之的是 WebMvcConﬁgurationSupport 方法，如下：
// */
//@Configuration
//public class MyInterceptorConfig extends WebMvcConfigurationSupport {
//
//  @Override
//  protected void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//    super.addInterceptors(registry);
//  }
//
//  /**
//   * 介绍了拦截器的定义和配置，但是这样是否就没问题了呢？
//   * 其实不然，如果使用上面这种配 置的话，我们会发现一个缺陷，那就是静态资源被拦截了。
//   * 可以在 resources/static/ 目录下放置一个图 片资源或者 html 文件，然后启动项目直接访问，
//   * 即可看到无法访问的现象。 也就是说，
//   * 虽然 Spring Boot 2.0 废弃了WebMvcConﬁgurerAdapter，
//   * 但是 WebMvcConﬁgurationSupport 又会导致默认的静态资源被拦截，这就需要我们手动将静态资源放 开。
//   * 如何放开呢？除了在 MyInterceptorConﬁg 配置类中重写 addInterceptors 方法外，
//   * 还需要再重写一 个方法： addResourceHandlers ，将静态资源放开：
//   */
//  /**
//   * 这样配置好之后，重启项目，静态资源也可以正常访问了。
//   * 如果你是个善于学习或者研究的人，那肯定 不会止步于此，没错，
//   * 上面这种方式的确能解决静态资源无法访问的问题，
//   * 但是，还有更方便的方式来 配置。
//   * @param registry
//   */
//  @Override
//  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//    super.addResourceHandlers(registry);
//  }
//}
