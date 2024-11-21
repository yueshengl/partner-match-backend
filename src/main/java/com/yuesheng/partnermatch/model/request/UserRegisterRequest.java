package com.yuesheng.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Dai
 * @Date: 2024/10/31 9:38
 * @Description: UserRegisterRequest 用户注册请求体
 * @Version: 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = -8479044281774259064L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
