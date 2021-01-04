package com.zihexin.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author 封心
 */
@SpringBootApplication
@EnableOpenApi
@ServletComponentScan
public class CourseApplication {

  public static void main(String[] args) {
      SpringApplication.run(CourseApplication.class, args);
  }

}
