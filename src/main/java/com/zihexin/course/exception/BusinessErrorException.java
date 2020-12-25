package com.zihexin.course.exception;

/**
 * 自定义异常
 *
 * @author 封心
 */
public class BusinessErrorException extends RuntimeException {

  private static final long serialVersionUId = -7480022450501760611L;

  /**
   * 异常码
   */
  private String code;

  /**
   * 异常提示信息
   */
  private String message;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
    this.code= businessMsgEnum.getCode();
    this.message= businessMsgEnum.getMsg();
  }
}
