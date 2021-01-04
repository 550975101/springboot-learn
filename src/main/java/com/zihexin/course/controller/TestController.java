package com.zihexin.course.controller;

import com.zihexin.course.entity.User;
import com.zihexin.course.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 封心
 */
@RestController
@RequestMapping(value = "/test", produces = "application/json; charset=UTF-8")
//@RequestMapping 是一个用来处理请求地址映射的注解，它可以用于类上，也可以用于方法上。
// 在类 的级别上的注解会将一个特定请求或者请求模式映射到一个控制器之上，
// 表示类中的所有响应请求的方 法都是以该地址作为父路径；在方法的级别表示进一步指定到处理方法的映射关系。
//该注解有6个属性，一般在项目中比较常用的有三个属性：value、method 和 produces。
//value 属性：指定请求的实际地址，value 可以省略不写 method 属性：指定请求的类型，
// 主要有 GET、PUT、POST、DELETE，默认为 GET produces属性：指定返回内容类型，
// 如 produces = "application/json; charset=UTF-8"
public class TestController {

  private final static Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  private UserService userService;

  @RequestMapping("/log")
  public String testLog() {
    logger.debug("======测试日志debug级别=======");
    logger.info("======测试日志info级别=======");
    logger.error("======测试日志error级别=======");
    logger.warn("======测试日志warn级别=======");
    //可以使用占位符 打印一些参数信息
    String str1 = "www.baidu.com";
    String str2 = "www.baidu.com";
    logger.info("========王某个人博客地址:{};王某的CSDN:{}", str1, str2);
    return "success";
  }

  @RequestMapping(value = "/get",method = RequestMethod.GET)
  public String testGet() {
    return "success";
  }

  //@PathVariable 注解主要是用来获取 url 参数，
  //Spring Boot 支持 restfull 风格的 url，比如一个 GET 请求携带一个参数 id 过来，
  //我们将 id 作为参数接收，可以使用 @PathVariable 注解
//  @GetMapping("/user/{id}")
//  public String testPathVariable(@PathVariable Integer id) {
//    logger.info("获取到的id为:{}", id);
//    return "success" + id;
//  }

  @GetMapping("/user/{idd}")
  public String testPathVariableSetValue(@PathVariable(value = "idd") Integer id) {
    logger.info("获取到的idd为:{}", id);
    return "success" + id;
  }

  @GetMapping("/user/{idd}/{name}")
  public String testPathVariable(@PathVariable(value = "idd") Integer id, @PathVariable String name) {
    logger.info("获取到的id为:{}", id);
    logger.info("获取到的name为:{}", name);
    return "success";
  }

  //http://localhost:8080/test/user?id=111
  //@RequestParam 注解顾名思义，也是获取请求参数的，
  //上面我们介绍了 @PathValiable 注解也是获 取请求参数的，
  //那么 @RequestParam 和 @PathVariable 有什么不同呢？
  //主要区别在于：@PathValiable 是从 url 模板中获取参数值， 即这种风格的 url： http://localhost:8080/user/{id} ；
  //而 @RequestParam 是从 request 里面获取参数值，即这种 风格的 url： http://localhost:8080/user?id=1
  //同样地，url 上面的参数和方法的参数需要一致
  @GetMapping("/user")
  public String testRequestParam(@RequestParam Integer id) {
    logger.info("获取的id为:{}", id);
    return "success";
  }

  //如果不一致，也需 要使用 value 属性来说明，比如 url 为： http://localhost:8080/user1?idd=1
  //除了 value 属性外，还有个两个属性比较常用： required 属性：true 表示该参数必须要传，否则就会报 404 错误，false 表示可有可无。
  //defaultValue 属性：默认值，表示如果请求中没有同名参数时的默认值。
  @GetMapping("/user1")
  public String testRequestParamSetValue(@RequestParam(value = "idd", required = false) Integer id) {
    logger.info("获取到的id为:{}", id);
    return "success";
  }

  @PostMapping("/form1")
  public String testForm(@RequestParam String userName, @RequestParam String password) {
    logger.info("获取到的userName为{}", userName);
    logger.info("获取到的password为{}", password);
    return "success";
  }

  //那么问题来了，如果表单数据很多，我们不可能在后台方法中写上很多参数，
  //每个参数还要 @RequestParam 注解。
  //针对这种情况，我们需要封装一个实体类来接收这些参数，实体中的属性名和 表单中的参数名一致即可。
  //不用加 @RequestBody 就可以映射  映射的表单数据
  //Content type 'application/x-www-form-urlencoded;charset=UTF-8'
  @PostMapping("/form2")
  public String testForm2(User user) {
    //username 和 userName  没映射上
    logger.info("获取到的userName为{}", user.getUsername());
    logger.info("获取到的password为{}", user.getPassword());
    return "success";
  }

  //@RequestBody 注解用于接收前端传来的实体，接收参数也是对应的实体，
  //比如前端通过 json 提交传 来两个参数 username 和 password，
  //此时我们需要在后端封装一个实体来接收。
  //加上@RequestBody  就得传json
  //Content type 'application/json;charset=UTF-8'
  // @RequestBody 注解用于 POST 请求上，接收 json 实体参数。
  //它和上面我们介绍的表单提 交有点类似，只不过参数的格式不同，一个是 json 实体，一个是表单提交。
  //在实际项目中根据具体场 景和需要使用对应的注解即可。
  @PostMapping("/user2")
  public String testRequestBody(@RequestBody User user) {
    logger.info("获取到的userName为{}", user.getUsername());
    logger.info("获取到的password为{}", user.getPassword());
    return "success";
  }

  @RequestMapping("/getUserByName/{name}")
  public User getUserByName(@PathVariable String name) {
    return userService.getUserByName(name);
  }

  @RequestMapping("/getUserByName/{id}/{name}")
  public User getUserByIdAndName(@PathVariable Long id,@PathVariable String name) {
    return userService.getUserByIdAndName(id, name);
  }

  @RequestMapping("/adduser")
  public String addUser(@RequestBody User user) {
    Integer integer = userService.insertUser(user);
    return integer == 0 ? "false" : "success";
  }

  @GetMapping("/userTest")
  public User getUser(HttpServletRequest request) {
    ServletContext servletContext = request.getServletContext();
    return (User) servletContext.getAttribute("user");
  }

  /**
   * 该 Controller 中是直接获取当前 session 中的用户数量，启动服务器，
   * 在浏览器中输入 localhost:8080/test/total 可以看到返回的结果是1，
   * 再打开一个浏览器，请求相同的地址可 以看到 count 是 2 ，这没有问题。
   * 但是如果关闭一个浏览器再打开，理论上应该还是2，
   * 但是实际测试 却是 3。
   * 原因是 session 销毁的方法没有执行（可以在后台控制台观察日志打印情况），
   * 当重新打开 时，服务器找不到用户原来的 session，于是又重新创建了一个 session，那怎么解决该问题呢
   * @param request
   * @return
   */
  @GetMapping("/total")
  public String getTotalUser(HttpServletRequest request) {
    ServletContext servletContext = request.getSession().getServletContext();
    Integer count = (Integer) servletContext.getAttribute("count");
    return "当前在线人数" + count;
  }

  @GetMapping("/total2")
  public String getTotalTUser(HttpServletRequest request, HttpServletResponse response) {
    Cookie cookie;
    try {
      cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
      cookie.setPath("/");
      //设置cookie有效期为2天，设置长一点
      cookie.setMaxAge(48 * 60 * 60);
      response.addCookie(cookie);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ServletContext servletContext = request.getSession().getServletContext();
    Integer count = (Integer) servletContext.getAttribute("count");
    return "当前在线人数" + count;
  }

  @GetMapping("/request")
  public String getRequestInfo(HttpServletRequest request) {
    logger.info("requestListener中得初始化器的name数据{}", request.getAttribute("name"));
    return "success";
  }
}
