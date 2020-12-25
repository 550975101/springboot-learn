package com.zihexin.course.exception;

/** 业务异常提示信息枚举类
 * @author 封心
 */
public enum BusinessMsgEnum {
  /**参数异常*/
  PARAMETER_EXCEPTION("102","参数异常"),
  /**等待超时*/
  SERVICE_TIME_OUT("103","服务调用超时"),
  /**参数过大*/
  PARAMETER_BIG_EXCEPTION("102","输入的图片数量不饿能超过9张"),
  /**500: 一劳永逸的提示可以在这里定义*/
  UNEXPECTED_EXCEPTION("500","系统发生异常,请联系管理员");
  //还可以定义更多业务的异常

  /**
   * 消息码
   */
  public String code;
  /**
   * 消息内容
   */
  public String msg;

  BusinessMsgEnum(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
