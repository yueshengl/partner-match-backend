package com.yuesheng.partnermatch.common;

/**
 * @Author: Dai
 * @Date: 2024/11/02 21:31
 * @Description: ErrorCode
 * @Version: 1.0
 */
public enum ErrorCode {
    PARAMS_ERROR(40000, "请求参数错误",""),
    NULL_ERROR(40001, "请求数据为空",""),
    NOT_LOGIN(40100, "未登录",""),
    NO_AUTH(40101, "无权限",""),
    SYSTEM_ERROR(50000, "系统内部异常",""),
    OPERATION_ERROR(50001, "操作失败","");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;
    /**
     * 描述
     */
    private final String description;

    ErrorCode(int code, String message,String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public String getDescription() {return description;}
}
