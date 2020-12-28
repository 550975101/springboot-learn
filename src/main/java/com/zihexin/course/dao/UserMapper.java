package com.zihexin.course.dao;
import java.util.List;

import nonapi.io.github.classgraph.json.Id;
import org.apache.ibatis.annotations.*;

import com.zihexin.course.entity.User;

/**
 * @author 封心
 */
@Mapper
public interface UserMapper {

  /**
   * 根据用户名获取user
   * @param username
   * @return
   */
  User getUserByName(String username);

  /**
   * 通过id获取user
   * 一般我们在设计表字段后，都会根据自动生成工具生成实体类，这样的话，基 本上实体类是能和表字段对应上的，
   * 起码也是驼峰对应的，由于在上面配置文件中开启了驼峰的配 置，所以字段都是能对的上的。
   * 但是，万一有对不上的呢？我们也有解决办法，使用 @Results 注解 来解决
   * @Results 中的 @Result 注解是用来指定每一个属性和字段的对应关系，这样的话就可以解决上面说 的这个问题了
   * @param id
   * @return
   */
  @Select("SELECT * FROM USER WHERE ID = #{id}")
  @Results(
    id = "userMap",
    value = {
      @Result(property = "id",column = "id"),
    @Result(property = "username", column = "user_name"),
    @Result(property = "password", column = "password")
    }
  )
  User getUser(Long id);

  /**
   * 根据id和用户名获取user
   * @Param 指定的参数应该要和 sql 中 #{} 取的参数名相同，不同则取不到。可以在 controller 中自行测试
   * @param id
   * @param userName
   * @return
   */
  @Select("SELECT * FROM USER WHERE ID = #{id} AND USER_NAME = #{name}")
  @ResultMap("userMap")
  User getUserByIdAndName(@Param("id") Long id,@Param("name") String  userName);

  /**
   * 插入数据
   * @param user
   * @return
   */
  @Insert("INSERT INTO USER (ID,USER_NAME,PASSWORD) VALUES (#{id},#{username},#{password})")
  Integer insertUser(User user);
}
