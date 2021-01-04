package com.zihexin.course.eventListener;

import com.zihexin.course.entity.User;
import com.zihexin.course.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 接下来，自定义一个监听器来监听上面定义的 MyEvent 事件，
 * 自定义监听器需要实现 ApplicationListener 接口即可
 *
 * 然后重写 onApplicationEvent 方法，将自定义的 MyEvent 事件传进来，
 * 因为该事件中，我们定义了 User 对象（该对象在实际中就是需要处理的数据，在下文来模拟），然后就可以使用该对象的信息 了。
 * OK，定义好了事件和监听器之后，需要手动发布事件，这样监听器才能监听到，这需要根据实际业务 场景来触发
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

  @Override
  public void onApplicationEvent(MyEvent myEvent) {
    //把事件中的信息获取到
    User user = myEvent.getUser();
    //处理时间 实际项目中可以通知别的服务或者处理其他逻辑
    System.out.println("用户名: " + user.getUsername());
    System.out.println("密码: " + user.getPassword());
  }
}
