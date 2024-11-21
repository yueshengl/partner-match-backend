package com.yuesheng.partnermatch.service;
import java.util.Date;

import com.yuesheng.partnermatch.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @Author: Dai
 * @Date: 2024/11/18 16:50
 * @Description: RedisTest
 * @Version: 1.0
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("yupiString","dog");
        valueOperations.set("yupiInt",1);
        valueOperations.set("yupiDouble",2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("yupi");
        valueOperations.set("yupiUser",user);
        //查
        Object yupi = valueOperations.get("yupiString");
        Assertions.assertTrue("dog".equals((String)yupi));
        yupi = valueOperations.get("yupiInt");
        Assertions.assertTrue(1==(Integer) yupi);
        yupi = valueOperations.get("yupiDouble");
        Assertions.assertTrue(2.0==(Double)yupi);
        yupi = valueOperations.get("yupiUser");
        System.out.println(yupi);

        //redisTemplate.opsForHash().put("yupi","yupiString","yu");


    }

}
