package com.zihexin.course.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

//在实际项目中，我们难免会遇到一些 null 值出现，
//我们转 json 时，是不希望有这些 null 出现的，
//比如 我们期望所有的 null 在转 json 时都变成 "" 这种空字符串，那怎么做呢？
//在 Spring Boot 中，我们做一 下配置即可，新建一个 jackson 的配置类
@Configuration
public class JacksonConfig {
  /**
   * 测试jackson请把 fastjson注释
   * @param builder
   * @return
   */
  @Bean
  @Primary
  @ConditionalOnMissingBean(ObjectMapper.class)
  public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    //null值 不参与序列化 还是参与吧 看业务 感觉不太友好
    //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
      @Override
      public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString("");
      }
    });
    return objectMapper;
  }
}
