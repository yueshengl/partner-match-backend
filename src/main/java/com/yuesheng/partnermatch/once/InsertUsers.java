package com.yuesheng.partnermatch.once;

import com.yuesheng.partnermatch.mapper.UserMapper;
import com.yuesheng.partnermatch.model.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * @Author: Dai
 * @Date: 2024/11/17 16:55
 * @Description: InsertUsers
 * @Version: 1.0
 */
@Component
public class InsertUsers {

    @Resource
    private UserMapper userMapper;

    /**
     * 批量插入数据
     */
   // @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE )
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("假月生");
            user.setUserAccount("yuesheng");
            //https://img2.woyaogexing.com/2022/07/04/2514244426169f7a!400x400.jpg
            user.setAvatarUrl("shanghai.myqcloud.com/shayu931/shayu.png");
            user.setUserProfile("一条咸鱼");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("123456789108");
            user.setEmail("yuesheng_shengyue@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("931");
            user.setTags("[]");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());

    }
}
