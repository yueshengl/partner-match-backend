package com.yuesheng.partnermatch.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Dai
 * @Date: 2024/11/19 15:29
 * @Description: RedissonConfig
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private String host;

    private String port;

    private String password;
    @Bean
    public RedissonClient redissonClient() {
        //1.创建配置
        Config config = new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s", host, port))
                .setDatabase(1)
                .setPassword(password);
        //2.根据Config创建出RedissonClient实例
        return Redisson.create(config);
    }
}
