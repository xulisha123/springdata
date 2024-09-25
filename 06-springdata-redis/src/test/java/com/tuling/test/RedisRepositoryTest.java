package com.tuling.test;

import com.tuling.config.RedisConfig;
import com.tuling.pojo.User;
import com.tuling.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@SpringJUnitConfig(classes = RedisConfig.class)
public class RedisRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void testAdd(){
        User user = new User();
        user.setId(1);
        user.setUsername("徐庶");
        repository.save(user);
    }


    @Test
    public void testSelect(){

        System.out.println(repository.findById(1));
    }

    @Test
    public void testDel(){
        repository.deleteById(1);
    }
}
