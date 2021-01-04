package com.zihexin.course.event;

import com.zihexin.course.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * 在实际项目中，我们往往需要自定义一些事件和监听器来满足业务场景，比如在微服务中会有这样的场 景：
 * 微服务 A 在处理完某个逻辑之后，需要通知微服务 B 去处理另一个逻辑，或者微服务 A 处理完某 个逻辑之后，
 * 需要将数据同步到微服务 B，这种场景非常普遍，
 * 这个时候，我们可以自定义事件以及监 听器来监听，
 * 一旦监听到微服务 A 中的某事件发生，就去通知微服务 B 处理对应的逻辑。
 */
public class MyEvent extends ApplicationEvent {

  private User user;

  public MyEvent(Object source, User user) {
    super(source);
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
