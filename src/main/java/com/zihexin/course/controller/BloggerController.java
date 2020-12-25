package com.zihexin.course.controller;

import com.google.common.collect.Lists;
import com.zihexin.course.entity.Blogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @author 封心
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

  @GetMapping("/getBlogger")
  public String getBlogger(Model model) {
    Blogger blogger = new Blogger(1L, "王某", "123456");
    model.addAttribute("blogger", blogger);
    return "blogger";
  }

  @GetMapping("/getList")
  public String getList(Model model) {
    Blogger blogger1 = new Blogger(1L, "王某", "123456");
    Blogger blogger2 = new Blogger(2L, "李某", "123456");
    ArrayList<Blogger> bloggers = Lists.newArrayList();
    bloggers.add(blogger1);
    bloggers.add(blogger2);
    model.addAttribute("bloggerList", bloggers);
    return "bloggerList";
  }
}
