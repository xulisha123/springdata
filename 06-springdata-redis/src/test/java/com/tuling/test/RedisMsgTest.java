package com.tuling.test;

import com.tuling.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisMsgTest {

    // 它是线程安全的
    @Autowired
    RedisTemplate redisTemplate;

    // 监听者
    @Test
    public void test() {
        while(true){

        }
    }

    // 发布者
    @Test
    public void test02() {
        redisTemplate.convertAndSend("log","1");

    }

}
