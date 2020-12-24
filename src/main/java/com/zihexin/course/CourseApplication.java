package com.zihexin.course;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author 封心
 */
@SpringBootApplication
@EnableOpenApi
public class CourseApplication {

  public static void main(String[] args) {
      SpringApplication.run(CourseApplication.class, args);
  }

}
