package com.zihexin.course.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 封心
 */
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/userRecord")
public class UserRecordController {

  @ApiOperation("分页查询所有数据")
  @GetMapping("/page")
  public void selectAll() {
    System.out.println(1111111111111111L);
  }
}
