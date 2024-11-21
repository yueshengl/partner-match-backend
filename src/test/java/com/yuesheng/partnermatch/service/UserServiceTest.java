package com.yuesheng.partnermatch.service;
import java.util.Arrays;
import java.util.List;

import com.yuesheng.partnermatch.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @Author: Dai
 * @Date: 2024/10/29 18:30
 * @Description: UserServiceTest
 * @Version: 1.0
 */
@SpringBootTest
public class UserServiceTest {


    @Resource
    private UserService userService;
    @Test
    public void testAddUser(){

        User user = new User();
        user.setUsername("yuesheng");
        user.setUserAccount("123");
        user.setAvatarUrl("https://profile-avatar.csdnimg.cn/7e813cc8db2f494e82c735d8315ac542_weixin_43950227.jpg!1");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister(){
        String userAccount = "yueshengd";
        String userPassword = "";
        String checkPassword = "123456";
        String planetCode = "1";

        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yueshengd";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yue shengd";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yuesheng";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yueshengd";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertTrue(result > 0);

    }

    @Test
    public void testSearchUserByTags(){
        List<String> tagNameList = Arrays.asList("java", "python");
        List<User> userList = userService.searchUserByTags(tagNameList);
        Assert.assertNotNull(userList);
    }
}