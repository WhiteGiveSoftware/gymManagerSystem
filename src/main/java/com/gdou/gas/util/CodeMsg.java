package com.gdou.gas.util;
//错误码统一处理类，所有的错误码统一定义在这里
public class CodeMsg {

    private int code;//错误码

    private String msg;//错误信息

    /**
     * 构造函数私有化即单例模式
     * @param code
     * @param msg
     */
    private CodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }



    public void setCode(int code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }

    //通用错误码定义
    //处理成功消息码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    //非法数据错误码
    public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
    public static CodeMsg CPACHA_EMPTY = new CodeMsg(-2, "验证码不能为空！");
    public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-3, "");
    public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "会话已失效，请刷新页面重试！");
    public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "验证码错误！");
    public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");




    public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-2002, "您所属的角色没有该权限！");

    //登录类错误码
    public static CodeMsg ADMIN_USERNAME_NO_EXIST = new CodeMsg(-3000, "该用户名不存在！");
    public static CodeMsg ADMIN_PASSWORD_ERROR = new CodeMsg(-3001, "密码错误！");
    public static CodeMsg ADMIN_Status_ERROR = new CodeMsg(-3002, "该用户还没激活，请登陆邮箱激活！");

    public static CodeMsg ADMIN_USER_ID_EMPTY = new CodeMsg(-6010, "用户ID不能为空！");
    public static CodeMsg ADMIN_USER_ID_ERROR = new CodeMsg(-6011, "用户ID错误！");

    public static CodeMsg ADMIN_USE_ADD_ERROR = new CodeMsg(-6002, "用户添加失败，请联系管理员！");
    public static CodeMsg ADMIN_USE_DELETE_ERROR = new CodeMsg(-6055, "用户删除失败，请联系管理员！");


    public static CodeMsg ADMIN_FIELD_ADD_ERROR = new CodeMsg(-6005, "场地添加失败！");
    public static CodeMsg ADMIN_FIELD_ID_EMPTY = new CodeMsg(-6666, "场地ID空");

    public static CodeMsg ADMIN_FIELD_ID_ERROR = new CodeMsg(-6161, "场地ID错误");
    public static CodeMsg ADMIN_FIELD_DELETE_ERROR = new CodeMsg(-6060, "场地删除失败");
}
