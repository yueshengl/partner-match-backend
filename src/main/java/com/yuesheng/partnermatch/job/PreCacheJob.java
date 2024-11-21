package com.yuesheng.partnermatch.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuesheng.partnermatch.model.domain.User;
import com.yuesheng.partnermatch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Dai
 * @Date: 2024/11/18 18:26
 * @Description: PreCacheJob
 * @Version: 1.0
 */
@Component
@Slf4j
public class PreCacheJob {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedissonClient redissonClient;
    //重点用户
    private List<Long> mainUserList = Arrays.asList(1L);

    // 每天晚上00:00执行一次, 预热推荐用户
    @Scheduled(cron = "0 29 18 * * ?")
    public void doCacheRecommendUser() {
        RLock lock = redissonClient.getLock("partnermatch:precachejob:docache:lock");
        try {
            //只有一个线程能获取到锁 等待时间0，过期时间-1才会启动自动续期
            if (lock.tryLock(0, -1,TimeUnit.MILLISECONDS)) {
                for (Long userId : mainUserList) {
                    log.info("getLock: " + Thread.currentThread().getId());
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);

                    String redisKey = String.format("partnermatch:user:recommend:%s", userId);
                    ValueOperations valueOperations = redisTemplate.opsForValue();
                    //写缓存
                    try {
                        valueOperations.set(redisKey, userPage, 1, TimeUnit.DAYS);
                    } catch (Exception e) {
                        log.error("redis set key error", e);
                    }
                }
            }else {
                log.info("notGetLock: " + Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommendUser error", e);
        } finally {
            //只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                log.info("unLock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
