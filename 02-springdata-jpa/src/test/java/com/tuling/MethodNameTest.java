package com.tuling;

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerMethodNameRepository;
import com.tuling.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MethodNameTest {

    // jdk动态代理的实例
    @Autowired
    CustomerMethodNameRepository repository;

    @Test
    public  void test01() {
        List<Customer> list = repository.findByCustName("李四");
        System.out.println(list);
    }

    @Test
    public  void test02() {
        boolean exists = repository.existsByCustName("xxx");
        System.out.println(exists);
    }

    @Test
    public  void test03() {
        int exists = repository.deleteByCustId(12L);
        System.out.println(exists);
    }


    @Test
    public  void test04() {
        List<Customer> list = repository.findByCustNameLike("徐%");
        System.out.println(list);
    }
}
