package com.yuesheng.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Dai
 * @Date: 2024/10/31 9:38
 * @Description: UserRegisterRequest 用户登录请求体
 * @Version: 1.0
 */
@Data
public class UserLoginRequest implements Serializable {


    private static final long serialVersionUID = 7167840858156818915L;
    private String userAccount;
    private String userPassword;
}
