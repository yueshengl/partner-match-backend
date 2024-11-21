package com.yuesheng.partnermatch.service;

import com.yuesheng.partnermatch.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author HUAWEI
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-10-29 18:25:17
*/
public interface UserService extends IService<User> {



    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @param planetCode 星球编码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafeUser(User originUser);


    /**
     * 用户注销
     * @param request
     * @return
     */
    String userLogout(HttpServletRequest request);


    /**
     * 根据标签搜索用户
     * @param tagList
     * @return
     */
    List<User> searchUserByTags(List<String> tagList);

    /**
     * 更新用户信息
     * @param user
     * @param loginUser
     * @return
     */
    int updateUser(User user, User loginUser);

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    boolean isAdmin(User loginUser);
}
