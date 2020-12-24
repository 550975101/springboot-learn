//package com.zihexin.course.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.google.common.collect.Lists;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 封心
// */
////使用 fastJson 时，对 null 的处理和 jackson
//// 有些不同，需要继承  WebMvcConfigurationSupport 类，
//// 然后覆盖 configureMessageConverters 方法，
//// 在方法中，我们可以选择对要实现 null 转换的 场景
////这个配置swagger3.0不能使用
//@Configuration
//public class fastJsonConfig extends WebMvcConfigurationSupport {
//
//  @Override
//  protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//    FastJsonConfig config = new FastJsonConfig();
//    config.setSerializerFeatures(
//      //保留map空的字段
//      SerializerFeature.WriteMapNullValue,
//      //将string类型的null转成""
//      SerializerFeature.WriteNullStringAsEmpty,
//      //将number类型的null转成0
//      SerializerFeature.WriteNullNumberAsZero,
//      //将list 类型的null 转为[]
//      SerializerFeature.WriteNullListAsEmpty,
//      //将boolean类型的null 转为false
//      SerializerFeature.WriteNullBooleanAsFalse,
//      //避免循环引用
//      SerializerFeature.DisableCircularReferenceDetect
//    );
//    converter.setFastJsonConfig(config);
//    converter.setDefaultCharset(Charset.forName("UTF-8"));
//    ArrayList<MediaType> mediaTypes = Lists.newArrayList();
//    //解决中文乱码 相当于再controller上的@RequestMapping中增加个属性produces=“application/json”
//    mediaTypes.add(MediaType.APPLICATION_JSON);
//    converter.setSupportedMediaTypes(mediaTypes);
//    converters.add(converter);
//  }
//}
