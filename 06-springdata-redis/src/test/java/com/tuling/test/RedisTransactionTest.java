package com.tuling.test;

import com.tuling.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 * 事务
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisTransactionTest {

    // 它是线程安全的
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate tranRedisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    // execute
    @Test
    public void testExecute() {

        // 一旦出现异常中断主线程， 因为它是同步的 .解决： 加上try catch
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // 如果在其他线程中发现了改变， 将回归事务
                operations.watch("execute");

                operations.multi(); // 开启事务
                // 无需在这里try 回滚事务， 出现异常底层会自动帮你回滚
                operations.opsForValue().set("execute","test");

                // 数据隔离性，事务没有提交 ， 查不到的
                System.out.println(operations.opsForValue().get("execute"));
                //int a=1/0;
                return operations.exec();   //提交
            }
        });

        // 上面redis事务出现了异常，下面业务正常执行
        System.out.println("业务操作");
        // todo: 执行了业务操作，

    }

    @Test
    public void testWatchExecute() {
        redisTemplate.opsForValue().set("execute","test11");
    }



    @Test
    @Transactional  // 底层用到动态代理， 底层会判断执行类型 写：使用带事务的connection . 如果是读 会创建新的connection
    @Commit
    public void testTransacational() {
        tranRedisTemplate.opsForValue().set("xxx","test11");
        System.out.println(tranRedisTemplate.keys("*"));

    }



}
