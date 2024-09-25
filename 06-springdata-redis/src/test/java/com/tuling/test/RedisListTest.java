package com.tuling.test;

import com.tuling.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisListTest {

    // 它是线程安全的
    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void test01() {

        BoundListOperations list = redisTemplate.boundListOps("list");
        for (int i = 0; i < 10; i++) {
            // 往尾部插入
            list.rightPush(i);
            // 往头部插入
            // list.leftPush()
            // 往尾部批量插入
            //list.rightPushAll(1,2,3,4);
            //list.leftPushAll(1,2,3,4);
        }
    }

    @Test
    public void test02() {

        BoundListOperations list = redisTemplate.boundListOps("list");
        // 范围获取
        // System.out.println(list.range(0, list.size()));
        // 根据下标获取某一个
        System.out.println(list.index(5));
    }

    @Test
    public void test03() {

        BoundListOperations list = redisTemplate.boundListOps("list");

        // 从头部删除指定数量
        //list.leftPop(2);
        // 从尾部删除指定数量
        //list.rightPop()

        // 根据值进行删除
        //list.remove(1,5);

        // 保留指定范围， 其余的都删掉
        //list.trim(1,2);

        // 数量
       // list.size();

        // 往指定的索引 添加/修改值
        list.set(1,10);
    }

}
