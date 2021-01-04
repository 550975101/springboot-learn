package com.zihexin.course.config;

import com.zihexin.course.monitor.MyHttpSessionListener;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpSessionListener;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author 封心
 */
@Configuration
public class Swagger3Config implements WebMvcConfigurer {

  private final SwaggerProperties swaggerProperties;

  public Swagger3Config(SwaggerProperties swaggerProperties) {
    this.swaggerProperties = swaggerProperties;
  }

  @Bean
  public Docket createApi() {
    return new Docket(DocumentationType.OAS_30)
      // 定义是否开启swagger，false为关闭，可以通过变量控制
      .enable(swaggerProperties.getEnable())
      .pathMapping("/")
      // 接口调试地址
      .host(swaggerProperties.getTryHost())
      //指定构建api文档的详细信息的方法apiInfo()
      .apiInfo(apiInfo())
      .select()
      //指定要生成api的接口的包路径,这里是扫描注解 带@ApiOperation
      .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
      //扫描路径 所有
      .paths(PathSelectors.any())
      .build()
      // 支持的通讯协议集合
      .protocols(newHashSet("https", "http"))
      // 授权信息设置，必要的header token等认证信息
      .securitySchemes(securitySchemes())
      // 授权信息全局应用
      .securityContexts(securityContexts());
  }

  /**
   * 设置授权信息
   */
  private List<SecurityScheme> securitySchemes() {
    ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
    return Collections.singletonList(apiKey);
  }

  /**
   * 授权信息全局应用
   */
  private List<SecurityContext> securityContexts() {
    return Collections.singletonList(
      SecurityContext.builder()
        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
        .build()
    );
  }

  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      // 设置页面标题
      .title("swagger3.0文档")
      //// 设置接口描述
      .description("文件描述")
      // 设置联系方式
      .contact(new Contact("封心", "www.baidu.com", "www.baidu.com"))
      //设置版本
      .version("1.0")
      .termsOfServiceUrl("http://localhost:8080")
      .build();
  }

  @SafeVarargs
  private final <T> Set<T> newHashSet(T... ts) {
    if (ts.length > 0) {
      return new LinkedHashSet<>(Arrays.asList(ts));
    }
    return null;
  }

  /**
   * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
   */
  @SuppressWarnings("unchecked")
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    try {
      Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
      List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
      if (registrations != null) {
        for (InterceptorRegistration interceptorRegistration : registrations) {
          interceptorRegistration
            .excludePathPatterns("/swagger**/**")
            .excludePathPatterns("/webjars/**")
            .excludePathPatterns("/v3/**")
            .excludePathPatterns("/doc.html");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Bean
  public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
    ServletListenerRegistrationBean<HttpSessionListener> listenerRegBean = new ServletListenerRegistrationBean<>();
    listenerRegBean.setListener(new MyHttpSessionListener());
    return listenerRegBean;
  }
}
