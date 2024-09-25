package com.tuling.test;

import com.tuling.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * set
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisSetTest {

    // 它是线程安全的
    @Autowired
    RedisTemplate redisTemplate;


    // 添加
    @Test
    public void testAdd(){
        BoundSetOperations set = redisTemplate.boundSetOps("set");

        set.add(1,2,3,4,5,6);
    }


    // 获取
    @Test
    public void testGet(){
        BoundSetOperations set = redisTemplate.boundSetOps("set");

        // 获取整个set
        System.out.println(set.members());
        // 获取个数
        System.out.println(set.size());
        // 根据某个元素判断是否在set中
        System.out.println(set.isMember(1));

        // 根据count获取随机元素 不带删除
        System.out.println(set.randomMembers(2));
        System.out.println(set.randomMembers(2));

        // 带删除
        //System.out.println(set.pop());

        SetOperations setOperations = redisTemplate.opsForSet();
        System.out.println(setOperations.pop("set", 2));
    }

    // Set运算操作


    @Test
    public void testAdd2(){
        BoundSetOperations set = redisTemplate.boundSetOps("set2");

        set.add(1,2,3,7,8,9);
    }

    // 交集
    @Test
    public void testCacl1() {

        BoundSetOperations set = redisTemplate.boundSetOps("set");
        set.intersectAndStore("set2","set3");

        BoundSetOperations set3 = redisTemplate.boundSetOps("set3");
        System.out.println(set3.members());
    }

    // 并集
    @Test
    public void testCacl2() {

        BoundSetOperations set = redisTemplate.boundSetOps("set");

        System.out.println(set.union("set2"));
    }

    // 差集
    @Test
    public void testCacl3() {

        BoundSetOperations set = redisTemplate.boundSetOps("set2");

        System.out.println(set.diff("set"));
    }
}
