package com.tuling.test;

import com.tuling.config.RedisConfig;
import com.tuling.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisStringTest {

    // 它是线程安全的
    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void test01(){
        // String类型
        BoundValueOperations username = redisTemplate.boundValueOps("username");
        // 10秒过期
        username.set("徐庶",10, TimeUnit.SECONDS);
    }


    @Test
    public void test02(){
        // String类型
        BoundValueOperations username = redisTemplate.boundValueOps("username");
        // 设置不存在的字符串  true=存入成功  false=失败
        System.out.println(username.setIfAbsent("诸葛"));
    }

    @Test
    public void test03(){
        // String类型
        System.out.println(redisTemplate.delete("username"));
    }


    @Test
    public void test04(){
        // String类型
        BoundValueOperations count = redisTemplate.boundValueOps("count");
        // 原子性累加  +1
        count.increment();
    }



    /**
     * 秒杀 1小时
     * 预热 100库存
     */
    @Test
    public void test05(){

        BoundValueOperations stock = redisTemplate.boundValueOps("stock");
        // 预热100个库存  1小时候过期
        stock.set(1,1,TimeUnit.HOURS);
    }

    // 秒杀的接口
    @Test
    public void test06(){

        BoundValueOperations stock = redisTemplate.boundValueOps("stock");

        if(stock.decrement()<0){
            System.out.println("库存不足");
        }
        else {
            System.out.println("秒杀成功");
        }
    }



    @Test
    public void test08(){

        BoundValueOperations stock = redisTemplate.boundValueOps("user");
        User user = new User();
        user.setId(1);
        user.setUsername("徐庶");
        stock.set(user);


    }


}
