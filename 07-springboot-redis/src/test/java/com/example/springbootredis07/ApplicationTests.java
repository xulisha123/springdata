package com.example.springbootredis07;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        BoundValueOperations springboot = redisTemplate.boundValueOps("springboot");
        springboot.set("hello");
    }

}
