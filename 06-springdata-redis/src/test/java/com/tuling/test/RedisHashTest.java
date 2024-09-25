package com.tuling.test;

import com.tuling.config.RedisConfig;
import com.tuling.pojo.User;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.concurrent.TimeUnit;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisHashTest {

    // 它是线程安全的
    @Autowired
    RedisTemplate redisTemplate;


    // hash
    @Test
    public void test01(){
        BoundHashOperations car = redisTemplate.boundHashOps("car");
        // set
        car.put("p_id",1);
        car.putIfAbsent("p_total",10);  // 不存在则存入
    }

    // 获取map
    @Test
    public void test02(){
        BoundHashOperations car = redisTemplate.boundHashOps("car");
        // set
        System.out.println(car.entries());
    }

    // 获取map某一项
    @Test
    public void test03(){
        BoundHashOperations car = redisTemplate.boundHashOps("car");
        // set
        System.out.println(car.get("p_total"));
    }

    // 删除map某一项
    @Test
    public void test05(){
        BoundHashOperations car = redisTemplate.boundHashOps("car");

        // 删除整个car
        //redisTemplate.delete("car");

        System.out.println(car.delete("p_id"));
    }


    // 获取map的长度
    @Test
    public void test06(){
        BoundHashOperations car = redisTemplate.boundHashOps("car");

        // 删除整个car
        //redisTemplate.delete("car");

        System.out.println(car.size());
    }


    // 对map中的某个value进行增量
    @Test
    public void test07(){
        BoundHashOperations car = redisTemplate.boundHashOps("car");

        car.increment("p_total",1);
    }


    // 对map中的某个value进行增量
    @Test
    public void testcar(){
        BoundHashOperations car = redisTemplate.boundHashOps("car"+99);

        car.put("p_"+15,10);
        car.put("p_"+16,2);
        car.put("p_"+17,1);
    }

    // 增加购物车商品
    @Test
    public void testaddCar(){
        BoundHashOperations car = redisTemplate.boundHashOps("car"+99);

        // 增加购物车商品
        // car.put("p_"+18,1);

        // 增加购物车数量
        //car.increment("p_"+15,1);

        // 商品总数
        //System.out.println(car.size());

        // 删除商品
        // car.delete("p_"+15);

        // 获得所有的key
        System.out.println(car.keys());

        // 获得所有的value
        //car.values()
    }

}
