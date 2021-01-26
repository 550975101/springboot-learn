package com.zihexin.course.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据上文，如果我要拦截所有 /admin 开头的 url 请求的话，需要在拦截器配置中添加这个前缀，
 * 但是 在实际项目中，可能会有这种场景出现：某个请求也是 /admin 开头的，
 * 但是不能拦截，比如 /admin/login 等等，这样的话又需要去配置。
 * 那么，可不可以做成一个类似于开关的东西，哪里不需 要拦截，我就在哪里弄个开关上去，做成这种灵活的可插拔的效果呢？
 * 是可以的，我们可以定义一个注解，该注解专门用来取消拦截操作，
 * 如果某个 Controller 中的方法我们 不需要拦截掉，即可在该方法上加上我们自定义的注解即可
 *
 * 该注解用来指定某个方法不用拦截
 * @author 封心
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnInterception {
}